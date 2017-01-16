package space.levan.memory.realm.presenter.impl;

import io.realm.RealmObject;

/**
 * Created by WangZhiYao on 2017/1/9.
 */

public interface ICollectionPresenter {

    void checkCollection(String isbn);

    void addCollection(RealmObject book);

    void cancelCollection(String isbn);
}
