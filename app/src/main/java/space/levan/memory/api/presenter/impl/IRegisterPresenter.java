package space.levan.memory.api.presenter.impl;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public interface IRegisterPresenter
{
    void userRegister(String nickname, String email, String username, String password);

    void cancelRegister();
}
