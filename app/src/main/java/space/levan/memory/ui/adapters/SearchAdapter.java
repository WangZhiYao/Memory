package space.levan.memory.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.model.bean.douban.Books;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/12/17
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY = 0;
    private static final int BOOKS = 1;

    private Context mContext;
    private List<Books> mBooks;
    private LayoutInflater mInflater;

    public SearchAdapter(Context context, List<Books> books) {
        this.mContext = context;
        this.mBooks = books;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY) {
            return new EmptyHolder(mInflater.inflate(R.layout.item_empty, parent, false));
        } else {
            return new ItemHolder(mInflater.inflate(R.layout.item_book_info, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyHolder) {
            ((EmptyHolder) holder).mTvEmptyMsg.setText("没有符合条件的结果");
            Drawable drawable = mContext.getDrawable(R.mipmap.img_search);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            ((EmptyHolder) holder).mTvEmptyMsg.setCompoundDrawables(null, drawable, null, null);
        } else {
            Glide.with(mContext)
                    .load(mBooks.get(position).getImages().getLarge())
                    .crossFade()
                    .into(((ItemHolder) holder).mIvBookCover);
            ((ItemHolder) holder).mTvBookTitle.setText(mBooks.get(position).getTitle());
            ((ItemHolder) holder).mTvBookInfo.setText(mBooks.get(position).getInfo());
            ((ItemHolder) holder).mTvBookDetail.setText(mBooks.get(position).getSummary());
        }
    }

    @Override
    public int getItemCount() {
        return mBooks.isEmpty() ? 1 : mBooks.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mBooks.size() == 0) {
            return EMPTY;
        } else {
            return BOOKS;
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_book_cover)
        ImageView mIvBookCover;
        @BindView(R.id.tv_book_title)
        TextView mTvBookTitle;
        @BindView(R.id.tv_book_info)
        TextView mTvBookInfo;
        @BindView(R.id.tv_book_detail)
        TextView mTvBookDetail;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_empty_msg)
        TextView mTvEmptyMsg;

        EmptyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void onRefreshData(List<Books> books) {
        mBooks.clear();
        mBooks.addAll(books);
        notifyDataSetChanged();
    }

    public void onAddData(List<Books> books) {
        mBooks.addAll(books);
        notifyDataSetChanged();
    }
}
