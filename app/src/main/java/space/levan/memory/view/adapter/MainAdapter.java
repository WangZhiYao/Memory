package space.levan.memory.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import io.realm.Realm;
import io.realm.RealmResults;
import space.levan.memory.R;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.bean.douban.ImageBean;
import space.levan.memory.bean.realm.Book;
import space.levan.memory.utils.UIUtils;
import space.levan.memory.view.activities.BookDetailActivity;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_DEFAULT = 1;
    Realm realm = Realm.getDefaultInstance();
    RealmResults<Book> mBooks = realm.where(Book.class).findAll();

    public MainAdapter(Context context)
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
            return new MainHolder(view);
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
        if (holder instanceof MainHolder)
        {
            final Book mBook = mBooks.get(position);
            Glide.with(mContext)
                    .load(mBook.image)
                    .into(((MainHolder) holder).iv_book_img);
            ((MainHolder) holder).tv_book_title.setText(mBook.title);
            ((MainHolder) holder).tv_book_info.setText(mBook.infoString);
            ((MainHolder) holder).tv_book_description.setText("\u3000" + mBook.summary);
            ((MainHolder) holder).itemView.setOnClickListener(view ->
            {
                Bundle b = new Bundle();
                BookInfoResponse bookInfoResponse = new BookInfoResponse();
                bookInfoResponse.setTitle(mBook.title);
                bookInfoResponse.setAuthor(new String[]{mBook.authors});
                ImageBean img = new ImageBean();
                img.setLarge(mBook.image);
                bookInfoResponse.setImages(img);
                bookInfoResponse.setIsbn13(mBook.isbn13);
                bookInfoResponse.setOrigin_title(mBook.origin_title);
                bookInfoResponse.setPages(mBook.pages);
                bookInfoResponse.setPubdate(mBook.pubdate);
                bookInfoResponse.setPublisher(mBook.publisher);
                bookInfoResponse.setSubtitle(mBook.subtitle);
                bookInfoResponse.setTranslator(mBook.translators == null ? new String[]{} : new String[]{mBook.translators});
                bookInfoResponse.setSummary(mBook.summary);
                b.putSerializable(BookInfoResponse.serialVersionName, bookInfoResponse);
                Bitmap bitmap;
                GlideBitmapDrawable imageDrawable = (GlideBitmapDrawable) ((MainHolder) holder).iv_book_img.getDrawable();
                if (imageDrawable != null)
                {
                    bitmap = imageDrawable.getBitmap();
                    b.putParcelable("book_img", bitmap);
                }
                Intent intent = new Intent(UIUtils.getContext(), BookDetailActivity.class);
                intent.putExtras(b);
                UIUtils.startActivity(intent);
            });
        }
        else
        {
            ((EmptyHolder) holder).iv_item_logo.setImageResource(R.mipmap.img_collection);
            ((EmptyHolder) holder).tv_item_tips.setText(R.string.ac_shelf_no_collection);
        }
    }

    @Override
    public int getItemCount()
    {
        return mBooks.isEmpty() ? 1 : mBooks.size();
    }

    class MainHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_book_img;
        private final TextView tv_book_title;
        private final TextView tv_book_info;
        private final TextView tv_book_description;

        public MainHolder(View itemView)
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
        private final ImageView iv_item_logo;
        private final TextView tv_item_tips;

        public EmptyHolder(View itemView)
        {
            super(itemView);
            iv_item_logo = (ImageView) itemView.findViewById(R.id.iv_item_logo);
            tv_item_tips = (TextView) itemView.findViewById(R.id.tv_item_tips);
        }
    }
}
