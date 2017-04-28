package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public interface IRegisterView
{
    void registerSuccess();

    void showMessage(String msg);

    void showProgress();

    void hideProgress();
}
