package space.levan.memory.api.presenter.impl;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface IRegisterPresenter {

    void userRegister(String nickname, String username, String password);

    void cancelRegister();
}
