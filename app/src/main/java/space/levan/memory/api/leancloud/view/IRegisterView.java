package space.levan.memory.api.leancloud.view;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public interface IRegisterView {

    void showMessage(String msg);

    void registerSuccess(String msg);

    void registerFailed(String msg);

    void showProgress();

    void hideProgress();
}
