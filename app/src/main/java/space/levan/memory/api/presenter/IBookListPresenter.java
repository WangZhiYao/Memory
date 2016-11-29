package space.levan.memory.api.presenter;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public interface IBookListPresenter {

    void loadBooks(String q, int start, int count, String fields);

    void cancelLoading();
}
