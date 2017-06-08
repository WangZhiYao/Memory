package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017/4/13.
 */

public interface ILoginModel
{

    void userLogin(String username, String password, ApiListener apiListener);

    void cancelLogin();
}
