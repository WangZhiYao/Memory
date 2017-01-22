package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface IRegisterView {

    void showMessage(String msg);

    void registerSuccess(String msg);

    void showProgress();

    void hideProgress();
}
