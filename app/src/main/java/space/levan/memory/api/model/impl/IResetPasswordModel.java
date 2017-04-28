package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public interface IResetPasswordModel
{
    void userResetPwd(String email, ApiListener listener);

    void cancelReset();
}
