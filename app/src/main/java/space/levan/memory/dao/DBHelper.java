package space.levan.memory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import space.levan.memory.dao.BookDatabase.BookInfo;

/**
 * 数据库
 *
 * Created by WangZhiYao on 2016/12/10.
 */

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Memory.db";
    private static final String TABLE_NAME = "Book";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME
                + " ("
                + BookInfo.BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookInfo.BOOK_IMG + " TEXT, "
                + BookInfo.BOOK_TITLE + " TEXT, "
                + BookInfo.BOOK_AUTHOR + " TEXT, "
                + BookInfo.BOOK_SUBTITLE + " TEXT, "
                + BookInfo.BOOK_ORIGIN_TITLE + " TEXT, "
                + BookInfo.BOOK_PUBLISHER + " TEXT, "
                + BookInfo.BOOK_TRANSLATOR + " TEXT, "
                + BookInfo.BOOK_PUBLISH_DATE + " TEXT, "
                + BookInfo.BOOK_PAGES + " TEXT, "
                + BookInfo.BOOK_ISBN + " TEXT, "
                + BookInfo.BOOK_SUMMARY + " TEXT"
                //+ BookInfo.BOOK_REVIEW + " TEXT"
                + ")");

        Log.w("WZY","Database Create success!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //TODO MOVE OR DELETE
    }

    public void openDB()
    {
        sqLiteDatabase = getWritableDatabase();
    }

    public void closeDB()
    {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
        {
            sqLiteDatabase.close();
        }
    }

    public long insert(String author, String title, String img,
                       String publisher, String subtitle, String origin_title,
                       String translator, String publish_date, String pages,
                       String isbn, String summary/*, String review*/)
    {
        ContentValues values = new ContentValues();
        values.put(BookInfo.BOOK_AUTHOR, author);
        values.put(BookInfo.BOOK_TITLE, title);
        values.put(BookInfo.BOOK_IMG, img);
        values.put(BookInfo.BOOK_PUBLISHER, publisher);
        values.put(BookInfo.BOOK_SUBTITLE, subtitle);
        values.put(BookInfo.BOOK_ORIGIN_TITLE, origin_title);
        values.put(BookInfo.BOOK_TRANSLATOR, translator);
        values.put(BookInfo.BOOK_PUBLISH_DATE, publish_date);
        values.put(BookInfo.BOOK_PAGES, pages);
        values.put(BookInfo.BOOK_ISBN, isbn);
        values.put(BookInfo.BOOK_SUMMARY, summary);
        //values.put(BookInfo.BOOK_REVIEW, review);

        return sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public long update(int id, String author, String title, String img,
                       String publisher, String subtitle, String origin_title,
                       String translator, String publish_date, String pages,
                       String isbn, String summary/*, String review*/)
    {
        ContentValues values = new ContentValues();
        values.put(BookInfo.BOOK_AUTHOR, author);
        values.put(BookInfo.BOOK_TITLE, title);
        values.put(BookInfo.BOOK_IMG, img);
        values.put(BookInfo.BOOK_PUBLISHER, publisher);
        values.put(BookInfo.BOOK_SUBTITLE, subtitle);
        values.put(BookInfo.BOOK_ORIGIN_TITLE, origin_title);
        values.put(BookInfo.BOOK_TRANSLATOR, translator);
        values.put(BookInfo.BOOK_PUBLISH_DATE, publish_date);
        values.put(BookInfo.BOOK_PAGES, pages);
        values.put(BookInfo.BOOK_ISBN, isbn);
        values.put(BookInfo.BOOK_SUMMARY, summary);
        //values.put(BookInfo.BOOK_REVIEW, review);

        String where = BookInfo.BOOK_ID + "=" + id;

        return sqLiteDatabase.update(TABLE_NAME, values, where, null);
    }

    public long delete(String ISBN)
    {
        String where = BookInfo.BOOK_ISBN + "=" + ISBN;

        return sqLiteDatabase.delete(TABLE_NAME, where, null);
    }

    public boolean isCollection(String ISBN)
    {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + BookInfo.BOOK_ISBN + " = " + ISBN, null);
        int i = 0;
        while (cursor.moveToNext())
        {
            i++;
        }
        cursor.close();
        return i > 0;
    }

    public Cursor getAllRecords()
    {
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
