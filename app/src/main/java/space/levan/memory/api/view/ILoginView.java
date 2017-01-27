package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface ILoginView {

    void showMessage(String msg);

    void loginSuccess(String msg);

    void showProgress();

    void hideProgress();

    void shake(int code);
}
