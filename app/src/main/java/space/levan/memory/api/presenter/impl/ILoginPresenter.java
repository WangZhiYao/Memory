package space.levan.memory.api.presenter.impl;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface ILoginPresenter {

    void userLogin(String username, String password);

    void cancelLogin();
}
