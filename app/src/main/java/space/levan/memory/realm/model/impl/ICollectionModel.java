package space.levan.memory.realm.model.impl;

import io.realm.RealmObject;
import space.levan.memory.realm.CompleteListener;

/**
 * Created by WangZhiYao on 2017/1/9.
 */

public interface ICollectionModel {

    void checkCollection(String isbn, CompleteListener listener);

    void addCollection(RealmObject book, CompleteListener listener);

    void cancelCollection(String isbn, CompleteListener listener);
}
