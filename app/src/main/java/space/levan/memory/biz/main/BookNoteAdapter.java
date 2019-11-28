package space.levan.memory.biz.main;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.annotation.TimePattern;
import space.levan.memory.db.BookNote;
import space.levan.memory.utils.TimeUtils;
import space.levan.memory.utils.glide.GlideApp;

/**
 * @author WangZhiYao
 * @date 2019/11/28
 */
public class BookNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookNote> mBookNoteList;

    public void setBookNoteList(List<BookNote> bookNoteList) {
        mBookNoteList = bookNoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookNoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mBookNoteList != null && !mBookNoteList.isEmpty()) {
            BookNote bookNote = mBookNoteList.get(position);
            if (bookNote != null) {
                ((BookNoteViewHolder) holder).bind(bookNote);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mBookNoteList == null ? 0 : mBookNoteList.size();
    }

    class BookNoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_cover)
        ImageView mIvBookCover;
        @BindView(R.id.tv_book_title)
        TextView mTvBookTitle;
        @BindView(R.id.tv_note_create_time)
        TextView mTvNoteCreateTime;
        @BindView(R.id.tv_book_note)
        TextView mTvBookNote;

        BookNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(BookNote bookNote) {
            if (!TextUtils.isEmpty(bookNote.getBookCover())) {
                GlideApp.with(itemView.getContext())
                        .load(bookNote.getBookCover())
                        .thumbnail(0.5f)
                        .transition(new DrawableTransitionOptions().crossFade())
                        .centerCrop()
                        .into(mIvBookCover);
            }

            if (!TextUtils.isEmpty(bookNote.getBookTitle())) {
                mTvBookTitle.setText(bookNote.getBookTitle());
            }

            if (bookNote.getCreateTime() != 0) {
                mTvNoteCreateTime.setText(TimeUtils.timestampToDateString(TimePattern.MINUTE, bookNote.getCreateTime()));
            }

            if (!TextUtils.isEmpty(bookNote.getNoteContent())) {
                mTvBookNote.setText(bookNote.getNoteContent());
            }
        }
    }
}
