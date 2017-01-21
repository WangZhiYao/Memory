package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface ILoginModel {

    void userLogin(String username, String password, ApiListener listener);

    void cancelLogin();
}
