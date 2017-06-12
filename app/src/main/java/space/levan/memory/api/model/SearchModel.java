package space.levan.memory.api.model;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.ISearchModel;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public class SearchModel implements ISearchModel
{
    @Override
    public void searchByISBN(String isbn, ApiListener apiListener)
    {
        /*
        RealmUtils.getInstance()
                .where(Book.class).equalTo("isbn13", isbn)
                .findAllAsync().<Book>asObservable()
                .subscribe(books -> apiListener.onSuccess(books.size() > 0),
                        throwable -> apiListener.onFailure(throwable.getMessage()));

        Realm realm = RealmUtils.getInstance();
        realm.where(Book.class).equalTo("isbn13", isbn)
                .findAllAsync().<Book>asObservable()
                .subscribe(books -> apiListener.onSuccess(books.size() > 0),
                        throwable -> apiListener.onFailure(throwable.getMessage()));*/
    }

    @Override
    public void cancelSearch()
    {

    }
}
