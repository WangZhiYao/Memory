package space.levan.memory.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import space.levan.memory.db.model.BookInfo;

/**
 * @author WangZhiYao
 * @date 2019/6/29
 */
@Dao
public interface BookInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertBook(BookInfo bookInfo);

    @Delete
    Completable deleteBook(BookInfo bookInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateBook(BookInfo bookInfo);

    @Query("SELECT * FROM BookInfo")
    LiveData<List<BookInfo>> queryAllBook();

    @Query("SELECT * FROM BookInfo WHERE isbn = :isbn")
    LiveData<List<BookInfo>> queryBookByIsbn(String isbn);

    @Query("DELETE FROM BookInfo WHERE id = :id")
    Completable deleteBookById(long id);
}
