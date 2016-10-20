package space.levan.memory.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SqliteHelper
 *
 * Created by WangZhiYao on 2016/10/20.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MEMORY.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS BOOK" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bookName VARCHAR," +
                "bookAuthor VARCHAR," +
                "bookPublisher VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        Log.w("onDBHelper","onUpgrade");
    }
}
