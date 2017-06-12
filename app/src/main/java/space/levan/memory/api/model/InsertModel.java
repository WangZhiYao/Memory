package space.levan.memory.api.model;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IInsertModel;
import space.levan.memory.bean.douban.BookInfoResponse;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public class InsertModel implements IInsertModel
{
    @Override
    public void insertInfo(BookInfoResponse bookInfoResponse, ApiListener apiListener)
    {
        /*
        try(Realm realm = RealmUtils.getInstance())
        {
            realm.executeTransactionAsync(realm1 ->
            {
                Book book = realm1.createObject(Book.class);
                book.isbn13 = bookInfoResponse.getIsbn13();
                book.title = bookInfoResponse.getTitle();
                book.subtitle = bookInfoResponse.getSubtitle();
                book.publisher = bookInfoResponse.getPublisher();
                book.authors = bookInfoResponse.getAuthors();
                book.translators = bookInfoResponse.getTranslators();
                book.pubdate = bookInfoResponse.getPubdate();
                book.pages = bookInfoResponse.getPages();
                book.origin_title = bookInfoResponse.getOrigin_title();
                book.image = bookInfoResponse.getImages().getLarge();
                book.summary = bookInfoResponse.getSummary();
                book.infoString = bookInfoResponse.getInfoString();
            }, () -> apiListener.onSuccess(true), error -> apiListener.onFailure(error.getMessage()));
        }


        RealmUtils.getInstance().asObservable()
                .subscribe(realm ->
                {
                    Book book = realm.createObject(Book.class);
                    book.isbn13 = bookInfoResponse.getIsbn13();
                    book.title = bookInfoResponse.getTitle();
                    book.subtitle = bookInfoResponse.getSubtitle();
                    book.publisher = bookInfoResponse.getPublisher();
                    book.authors = bookInfoResponse.getAuthors();
                    book.translators = bookInfoResponse.getTranslators();
                    book.pubdate = bookInfoResponse.getPubdate();
                    book.pages = bookInfoResponse.getPages();
                    book.origin_title = bookInfoResponse.getOrigin_title();
                    book.image = bookInfoResponse.getImages().getLarge();
                    book.summary = bookInfoResponse.getSummary();
                    book.infoString = bookInfoResponse.getInfoString();

                    apiListener.onSuccess(true);
                }, throwable -> apiListener.onFailure(throwable.getMessage()));


        Realm realm = RealmUtils.getInstance();
        realm.asObservable()
                .subscribe(realm1 ->
                {
                    Book book = realm1.createObject(Book.class);
                    book.isbn13 = bookInfoResponse.getIsbn13();
                    book.title = bookInfoResponse.getTitle();
                    book.subtitle = bookInfoResponse.getSubtitle();
                    book.publisher = bookInfoResponse.getPublisher();
                    book.authors = bookInfoResponse.getAuthors();
                    book.translators = bookInfoResponse.getTranslators();
                    book.pubdate = bookInfoResponse.getPubdate();
                    book.pages = bookInfoResponse.getPages();
                    book.origin_title = bookInfoResponse.getOrigin_title();
                    book.image = bookInfoResponse.getImages().getLarge();
                    book.summary = bookInfoResponse.getSummary();
                    book.infoString = bookInfoResponse.getInfoString();

                    apiListener.onSuccess(true);
                }, throwable -> apiListener.onFailure(throwable.getMessage()));*/
    }

    @Override
    public void cancelInsert()
    {

    }
}
