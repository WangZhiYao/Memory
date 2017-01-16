package space.levan.memory.api.leancloud.view;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public interface ILoginView {

    void showMessage(String msg);

    void loginSuccess(String msg);

    void loginFailed(String msg);

    void showProgress();

    void hideProgress();
}
