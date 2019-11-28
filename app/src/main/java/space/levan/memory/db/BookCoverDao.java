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
import space.levan.memory.db.model.BookCover;

/**
 * @author WangZhiYao
 * @date 2019/6/29
 */
@Dao
public interface BookCoverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCover(BookCover bookCover);

    @Delete
    Completable deleteCover(BookCover bookCover);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateCover(BookCover bookCover);

    @Query("SELECT * FROM BookCover")
    LiveData<List<BookCover>> queryAllCovers();

    @Query("SELECT * FROM BookCover WHERE isbn = :isbn")
    LiveData<List<BookCover>> queryCoversByIsbn(String isbn);
}