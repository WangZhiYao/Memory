package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public interface IRegisterModel
{
    void userRegister(String nickname, String email, String username, String password, ApiListener listener);

    void cancelRegister();
}
