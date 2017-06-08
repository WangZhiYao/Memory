package space.levan.memory.utils;

import android.text.TextUtils;

import io.realm.Realm;
import io.realm.RealmResults;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.bean.realm.Book;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class RealmUtils
{
    public static Boolean checkCollection(String isbn)
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo("isbn13", isbn).findAll();
        return results.size() > 0;
    }

    public static Boolean addCollection(BookInfoResponse book)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 ->
        {
            Book mBook = realm1.createObject(Book.class);
            mBook.authors = book.getAuthors();
            mBook.image = book.getImages().getLarge();
            mBook.isbn13 = book.getIsbn13();
            mBook.origin_title = book.getOrigin_title();
            mBook.pages = book.getPages();
            mBook.pubdate = book.getPubdate();
            mBook.publisher = book.getPublisher();
            mBook.subtitle = book.getSubtitle();
            mBook.summary = book.getSummary();
            mBook.title = book.getTitle();
            mBook.infoString = book.getInfoString();
            if (!TextUtils.equals(book.getTranslators(),""))
            {
                mBook.translators = book.getTranslators();
            }
        });

        return true;
    }

    public static Boolean cancelCollection(String isbn)
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo("isbn13", isbn).findAll();
        realm.executeTransaction(realm1 -> results.deleteAllFromRealm());
        return false;
    }
}
