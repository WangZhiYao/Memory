package space.levan.memory.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import space.levan.memory.Constants;
import space.levan.memory.db.converters.ListConverter;
import space.levan.memory.db.model.BookCover;
import space.levan.memory.db.model.BookInfo;
import space.levan.memory.db.model.Note;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
@Database(entities = {BookInfo.class, BookCover.class, Note.class}, version = 1, exportSchema = false)
@TypeConverters(ListConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase INSTANCE;

    public abstract BookInfoDao bookInfoDao();

    public abstract BookCoverDao bookCoversDao();

    public abstract NoteDao noteDao();

    public abstract BookNoteDao bookNoteDao();

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, Constants.DB_NAME)
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
