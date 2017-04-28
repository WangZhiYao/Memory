package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2017/4/13.
 */

public interface ILoginView
{
    void loginSuccess();

    void showMessage(String msg);

    void showProgress();

    void hideProgress();
}
