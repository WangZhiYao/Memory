package space.levan.memory.api.presenter.impl;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public interface IResetPasswordPresenter
{
    void userResetPwd(String email);

    void cancelReset();
}
