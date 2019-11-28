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
import space.levan.memory.db.model.Note;

/**
 * @author WangZhiYao
 * @date 2019/6/29
 */
@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNote(Note note);

    @Delete
    Completable deleteNote(Note note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateNote(Note note);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> queryAllNote();

    @Query("SELECT * FROM Note WHERE isbn = :isbn")
    LiveData<List<Note>> queryNoteByIsbn(String isbn);
}