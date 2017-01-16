package space.levan.memory.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import io.realm.Realm;
import io.realm.RealmResults;
import space.levan.memory.R;
import space.levan.memory.bean.Book;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.bean.douban.ImageBean;
import space.levan.memory.utils.UIUtils;
import space.levan.memory.view.activity.BaseActivity;
import space.levan.memory.view.activity.BookDetailActivity;

/**
 * Created by WangZhiYao on 2017/1/10.
 */

public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_DEFAULT = 1;
    Realm realm = Realm.getDefaultInstance();
    RealmResults<Book> mBooks = realm.where(Book.class).equalTo("user", AVUser.getCurrentUser().getUsername()).findAll();

    public CollectionAdapter(Context context)
    {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        if (viewType == TYPE_DEFAULT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
            return new CollectionHolder(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            return new EmptyHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if (mBooks.size() == 0 || mBooks.isEmpty())
        {
            return TYPE_EMPTY;
        }
        else
        {
            return TYPE_DEFAULT;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof CollectionHolder)
        {
            final Book mBook = mBooks.get(position);
            Glide.with(mContext)
                    .load(mBook.getImg())
                    .into(((CollectionHolder) holder).iv_book_img);
            ((CollectionHolder) holder).tv_book_title.setText(mBook.getTitle());
            ((CollectionHolder) holder).tv_book_info.setText(mBook.getInfoString());
            ((CollectionHolder) holder).tv_book_description.setText("\u3000" + mBook.getSummary());
            ((CollectionHolder) holder).itemView.setOnClickListener(view ->
            {
                Bundle b = new Bundle();
                BookInfoResponse bookInfoResponse = new BookInfoResponse();
                bookInfoResponse.setTitle(mBook.getTitle());
                bookInfoResponse.setAuthor(new String[]{mBook.getAuthor()});
                ImageBean img = new ImageBean();
                img.setLarge(mBook.getImg());
                bookInfoResponse.setImages(img);
                bookInfoResponse.setIsbn13(mBook.getIsbn());
                bookInfoResponse.setOrigin_title(mBook.getOrigin_title());
                bookInfoResponse.setPages(mBook.getPages());
                bookInfoResponse.setPubdate(mBook.getPubdate());
                bookInfoResponse.setPublisher(mBook.getPublisher());
                bookInfoResponse.setSubtitle(mBook.getSubtitle());
                bookInfoResponse.setTranslator(mBook.getTranslator() == null ? new String[]{} : new String[]{mBook.getTranslator()});
                bookInfoResponse.setSummary(mBook.getSummary());
                b.putSerializable(bookInfoResponse.serialVersionName, bookInfoResponse);
                Bitmap bitmap;
                GlideBitmapDrawable imageDrawable = (GlideBitmapDrawable) ((CollectionHolder) holder).iv_book_img.getDrawable();
                if (imageDrawable != null)
                {
                    bitmap = imageDrawable.getBitmap();
                    b.putParcelable("book_img", bitmap);
                }
                Intent intent = new Intent(UIUtils.getContext(), BookDetailActivity.class);
                intent.putExtras(b);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    if (BaseActivity.activity == null)
                    {
                        UIUtils.startActivity(intent);
                        return;
                    }
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(BaseActivity.activity, ((CollectionHolder) holder).iv_book_img, "book_img");
                    BaseActivity.activity.startActivity(intent, options.toBundle());
                }
                else
                {
                    UIUtils.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mBooks.isEmpty() ? 1 : mBooks.size();
    }

    class CollectionHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_book_img;
        private final TextView tv_book_title;
        private final TextView tv_book_info;
        private final TextView tv_book_description;

        public CollectionHolder(View itemView)
        {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            tv_book_description = (TextView) itemView.findViewById(R.id.tv_book_description);
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder
    {
        public EmptyHolder(View itemView)
        {
            super(itemView);
        }
    }
}
