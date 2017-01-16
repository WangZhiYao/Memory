package space.levan.memory.api.leancloud.presenter.impl;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public interface IRegisterPresenter {

    void userRegister(String nickname, String username, String password);

    void cancelRegister();
}
