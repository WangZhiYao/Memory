package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface IBookListView {

    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);

    void addData(Object result);
}
