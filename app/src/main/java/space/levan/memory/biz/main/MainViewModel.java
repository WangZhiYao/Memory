package space.levan.memory.biz.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import space.levan.memory.db.AppDataBase;
import space.levan.memory.db.BookNote;
import space.levan.memory.db.BookNoteDao;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class MainViewModel extends AndroidViewModel {

    private BookNoteDao mBookNoteDao;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mBookNoteDao = AppDataBase.getInstance(application).bookNoteDao();
    }

    public LiveData<List<BookNote>> queryAllBookNote() {
        return mBookNoteDao.queryAllNote();
    }
}
