package space.levan.memory.api.leancloud.presenter.impl;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public interface ILoginPresenter {

    void userLogin(String username, String password);

    void cancelLogin();
}
