package space.levan.memory.api.leancloud.model.impl;

import space.levan.memory.api.ApiCompleteListener;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public interface ILoginModel {

    void userLogin(String username, String password, ApiCompleteListener listener);

    void cancelLogin();
}
