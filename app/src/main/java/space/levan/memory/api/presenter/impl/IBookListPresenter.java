package space.levan.memory.api.presenter.impl;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface IBookListPresenter {

    void loadBooks(String q, int start, int count, String fields);

    void cancelLoading();
}
