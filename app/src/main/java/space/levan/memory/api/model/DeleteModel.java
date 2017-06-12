package space.levan.memory.api.model;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IDeleteModel;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public class DeleteModel implements IDeleteModel
{
    @Override
    public void deleteInfo(String isbn, ApiListener apiListener)
    {
        /*
        try(Realm realm = RealmUtils.getInstance())
        {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Book> books = realm.where(Book.class).equalTo("isbn13", isbn).findAll();
                    Log.w("WZY", "before : "+books.size() + "");
                    books.deleteAllFromRealm();
                    Log.w("WZY", "after : " + books.size() + "");
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    apiListener.onSuccess(false);
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    apiListener.onFailure(error.getMessage());
                }
            });
        }


        RealmUtils.getInstance()
                .where(Book.class).equalTo("isbn13", isbn)
                .findAllAsync().<Book>asObservable()
                .subscribe(books ->
                {
                    books.deleteAllFromRealm();
                    apiListener.onSuccess(false);
                }, throwable -> apiListener.onFailure(throwable.getMessage()));



        Realm realm = RealmUtils.getInstance();
        realm.where(Book.class).equalTo("isbn13", isbn)
                .findAllAsync().<Book>asObservable()
                .subscribe(books ->
                {
                    books.deleteAllFromRealm();
                    apiListener.onSuccess(false);
                }, throwable -> apiListener.onFailure(throwable.getMessage()));
        realm.close();*/
    }

    @Override
    public void cancelDelete()
    {

    }
}
