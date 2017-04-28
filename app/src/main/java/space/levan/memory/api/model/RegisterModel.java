package space.levan.memory.api.model;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IRegisterModel;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class RegisterModel implements IRegisterModel
{
    @Override
    public void userRegister(String nickname, String email, String username, String password, ApiListener listener)
    {

    }

    @Override
    public void cancelRegister()
    {

    }
}
