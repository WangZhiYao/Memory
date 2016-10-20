package space.levan.memory.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import space.levan.memory.bean.Book;

/**
 * 对Book表中数据的增删改查
 *
 * Created by WangZhiYao on 2016/10/20.
 */

public class BookDao {

    private DBHelper dbHelper;
    private String DATABASE_TABLE_NAME = "BOOK";
    private String BOOK_NAME = "bookName";
    private String BOOK_AUTHOR = "bookAuthor";
    private String BOOK_PUBLISHER = "bookPublisher";

    public BookDao(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insert(Book book)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_NAME, book.getBookName());
        contentValues.put(BOOK_AUTHOR, book.getBookAuthor());
        contentValues.put(BOOK_PUBLISHER, book.getBookPublisher());
        long id = sqLiteDatabase.insert(DATABASE_TABLE_NAME, null, contentValues);
        book.setId(id);
        sqLiteDatabase.close();
    }

    public int delete(long id)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.close();
        return sqLiteDatabase.delete(DATABASE_TABLE_NAME, "_id=?", new String[]{id + ""});
    }

    public int update(Book book)
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_NAME, book.getBookName());
        contentValues.put(BOOK_AUTHOR, book.getBookAuthor());
        contentValues.put(BOOK_PUBLISHER, book.getBookPublisher());
        dbHelper.close();
        return sqLiteDatabase.update(DATABASE_TABLE_NAME, contentValues, "_id=?", new String[]{book.getId() + ""});
    }

    public List<Book> queryAll()
    {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_NAME, null, null, null, null, null, null);
        List<Book> bookList = new ArrayList<Book>();
        while (cursor.moveToNext())
        {
            bookList.add(new Book(cursor.getLong(cursor.getColumnIndex("_id")),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
        sqLiteDatabase.close();
        return bookList;
    }
}
