package space.levan.memory.biz.newnote;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.Completable;
import space.levan.memory.db.AppDataBase;
import space.levan.memory.db.BookCoverDao;
import space.levan.memory.db.BookInfoDao;
import space.levan.memory.db.NoteDao;
import space.levan.memory.db.model.BookCover;
import space.levan.memory.db.model.BookInfo;
import space.levan.memory.db.model.Note;

/**
 * @author WangZhiYao
 * @date 2019/8/3
 */
public class NewNoteViewModel extends AndroidViewModel {

    private BookInfoDao mBookInfoDao;
    private BookCoverDao mBookCoverDao;
    private NoteDao mNoteDao;

    public NewNoteViewModel(@NonNull Application application) {
        super(application);

        mBookInfoDao = AppDataBase.getInstance(application).bookInfoDao();
        mBookCoverDao = AppDataBase.getInstance(application).bookCoversDao();
        mNoteDao = AppDataBase.getInstance(application).noteDao();
    }

    public Completable insertBookNote(@NonNull BookInfo bookInfo, @NonNull BookCover bookCover, @NonNull Note note) {
        return Completable.mergeArray(mBookInfoDao.insertBook(bookInfo),
                mBookCoverDao.insertCover(bookCover),
                mNoteDao.insertNote(note));
    }
}
