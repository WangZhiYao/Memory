package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public interface IRegisterModel
{
    void userRegister(String email, String password, ApiListener apiListener);

    void cancelRegister();
}
