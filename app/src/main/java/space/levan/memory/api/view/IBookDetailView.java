package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public interface IBookDetailView {

    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void updateView(Object result);
}
