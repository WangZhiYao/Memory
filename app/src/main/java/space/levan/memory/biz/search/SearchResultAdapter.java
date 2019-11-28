package space.levan.memory.biz.search;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.api.model.Book;
import space.levan.memory.api.model.Images;
import space.levan.memory.utils.glide.GlideApp;

/**
 * @author WangZhiYao
 * @date 2019/7/5
 */
public class SearchResultAdapter extends PagedListAdapter<Book, RecyclerView.ViewHolder> {

    private static DiffUtil.ItemCallback<Book> SEARCH_RESULT_CALLBACK = new DiffUtil.ItemCallback<Book>() {

        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    private OnBookClickListener mOnBookClickListener;

    public SearchResultAdapter() {
        super(SEARCH_RESULT_CALLBACK);
    }

    public void setOnBookClickListener(OnBookClickListener onBookClickListener) {
        mOnBookClickListener = onBookClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getCurrentList() != null) {
            Book book = getItem(position);
            if (book != null) {
                ((BookViewHolder) holder).bind(book);
            }
        }
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_cover)
        ImageView mIvBookCover;
        @BindView(R.id.tv_book_title)
        TextView mTvBookTitle;
        @BindView(R.id.tv_book_author)
        TextView mTvBookAuthor;
        @BindView(R.id.tv_book_summary)
        TextView mTvBookSummary;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull Book book) {
            String cover = book.getImage();

            Images images = book.getImages();
            if (images != null && !TextUtils.isEmpty(images.getSmall())) {
                cover = images.getSmall();
            }

            if (!TextUtils.isEmpty(cover)) {
                GlideApp.with(itemView.getContext())
                        .load(cover)
                        .thumbnail(0.5f)
                        .transition(new DrawableTransitionOptions().crossFade())
                        .centerCrop()
                        .into(mIvBookCover);
            }

            String title = book.getTitle();
            if (!TextUtils.isEmpty(title)) {
                mTvBookTitle.setText(title);
            }

            StringBuilder authorBuilder = new StringBuilder();
            List<String> authors = book.getAuthor();
            if (authors != null && !authors.isEmpty()) {
                for (String author : authors) {
                    authorBuilder.append(author).append(", ");
                }

                if (authorBuilder.length() > 0) {
                    authorBuilder.deleteCharAt(authorBuilder.lastIndexOf(", "));
                }
            }

            if (authorBuilder.length() > 0) {
                mTvBookAuthor.setText(authorBuilder.toString());
            }

            String summary = book.getSummary();
            if (!TextUtils.isEmpty(summary)) {
                mTvBookSummary.setText(summary);
            }

            itemView.setOnClickListener(view -> {
                if (mOnBookClickListener != null) {
                    mOnBookClickListener.onBookClicked(book);
                }
            });
        }
    }

    public interface OnBookClickListener {

        /**
         * 书籍点击事件
         *
         * @param book
         */
        void onBookClicked(@NonNull Book book);
    }
}
