package space.levan.memory.realm.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import space.levan.memory.bean.Book;
import space.levan.memory.realm.CompleteListener;
import space.levan.memory.realm.model.impl.ICollectionModel;

/**
 * Created by WangZhiYao on 2017/1/9.
 */

public class CollectionModel implements ICollectionModel {

    @Override
    public void checkCollection(String isbn, CompleteListener listener)
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo("isbn", isbn).findAll();
        if (results.size() > 0)
        {
            listener.isCollection(true);
        }
        else
        {
            listener.isCollection(false);
        }
    }

    @Override
    public void addCollection(RealmObject book, CompleteListener listener)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(book);
        realm.commitTransaction();
        listener.onAddSuccess();
    }

    @Override
    public void cancelCollection(String isbn, CompleteListener listener)
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo("isbn", isbn).findAll();
        realm.executeTransaction(realm1 ->
        {
            results.deleteAllFromRealm();
            listener.onCancelSuccess();
        });
    }
}
