package space.levan.memory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import space.levan.memory.bean.Book2;

/**
 * 对数据库进行操作
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public class BookDao {

    private DBHelper dbHelper;
    private String DATABASE_TABLE_NAME = "BOOK";
    private String BOOK_NAME = "bookName";
    private String BOOK_AUTHOR = "bookAuthor";
    private String BOOK_PUBLISHER = "bookPublisher";

    public BookDao(Context mContext)
    {
        dbHelper = new DBHelper(mContext);
    }

    public void insert(Book2 mBook2)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_NAME, mBook2.getTitle());
        contentValues.put(BOOK_AUTHOR, mBook2.getAuthor());
        contentValues.put(BOOK_PUBLISHER, mBook2.getPublisher());
        mBook2.setId(sqLiteDatabase.insert(DATABASE_TABLE_NAME, null, contentValues));
        sqLiteDatabase.close();
    }

    public int delete(long id)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int count = sqLiteDatabase.delete(DATABASE_TABLE_NAME, "_id=?", new String[]{id + ""});
        sqLiteDatabase.close();
        return count;
    }

    public int update(Book2 mBook2)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_NAME, mBook2.getTitle());
        contentValues.put(BOOK_AUTHOR, mBook2.getAuthor());
        contentValues.put(BOOK_PUBLISHER, mBook2.getPublisher());
        int count = sqLiteDatabase.update(DATABASE_TABLE_NAME, contentValues, "_id=?", new String[]{mBook2.getId() + ""});
        sqLiteDatabase.close();
        return count;
    }

    public List<Book2> queryAll()
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_NAME, null,null,null,null,null,null,null);
        List<Book2> mBook2List = new ArrayList<Book2>();
        while (cursor.moveToNext())
        {
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String bookName = cursor.getString(1);
            String bookAuthor = cursor.getString(2);
            String bookPublisher = cursor.getString(3);
            mBook2List.add(new Book2(id, bookName, bookAuthor, bookPublisher));
        }
        cursor.close();
        sqLiteDatabase.close();
        return mBook2List;
    }
}
