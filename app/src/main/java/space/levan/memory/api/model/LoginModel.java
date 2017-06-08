package space.levan.memory.api.model;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.ILoginModel;

/**
 * Created by WangZhiYao on 2017/4/13.
 */

public class LoginModel implements ILoginModel
{
    @Override
    public void userLogin(String username, String password, ApiListener listener)
    {

    }

    @Override
    public void cancelLogin()
    {

    }
}
