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

import java.util.List;

import space.levan.memory.R;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.utils.UIUtils;
import space.levan.memory.view.activities.BookDetailActivity;

/**
 * Created by WangZhiYao on 2017/4/28.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_DEFAULT = 1;
    private final List<BookInfoResponse> bookInfoResponses;
    private Context mContext;
    private int columns;

    public SearchAdapter(Context context, List<BookInfoResponse> responses, int columns)
    {
        this.bookInfoResponses = responses;
        this.columns = columns;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        if (viewType == TYPE_DEFAULT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
            return new SearchHolder(view);
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
        if (bookInfoResponses == null || bookInfoResponses.isEmpty())
        {
            return TYPE_EMPTY;
        }
        else
        {
            return TYPE_DEFAULT;
        }
    }

    public int getItemColumnSpan(int position)
    {
        switch (getItemViewType(position))
        {
            case TYPE_DEFAULT:
                return 1;
            default:
                return columns;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof SearchHolder)
        {
            final BookInfoResponse bookInfo = bookInfoResponses.get(position);
            Glide.with(mContext)
                    .load(bookInfo.getImages().getLarge())
                    .into(((SearchHolder) holder).iv_book_img);
            ((SearchHolder) holder).tv_book_title.setText(bookInfo.getTitle());
            ((SearchHolder) holder).tv_book_info.setText(bookInfo.getInfoString());
            ((SearchHolder) holder).tv_book_description.setText("　　" + bookInfo.getSummary());
            ((SearchHolder) holder).itemView.setOnClickListener(v ->
            {
                Bundle b = new Bundle();
                b.putSerializable(BookInfoResponse.serialVersionName, bookInfo);
                Bitmap bitmap;
                GlideBitmapDrawable imageDrawable = (GlideBitmapDrawable) ((SearchHolder) holder).iv_book_img.getDrawable();
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
            ((EmptyHolder) holder).iv_item_logo.setImageResource(R.mipmap.img_search);
            ((EmptyHolder) holder).tv_item_tips.setText(R.string.ac_search_no_result);
        }
    }

    @Override
    public int getItemCount()
    {
        return bookInfoResponses.isEmpty() ? 1 : bookInfoResponses.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_book_img;
        private final TextView tv_book_title;
        private final TextView tv_book_info;
        private final TextView tv_book_description;
        public SearchHolder(View itemView)
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