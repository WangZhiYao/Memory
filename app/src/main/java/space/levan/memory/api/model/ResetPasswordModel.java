package space.levan.memory.api.model;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IResetPasswordModel;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class ResetPasswordModel implements IResetPasswordModel
{
    @Override
    public void userResetPwd(String email, ApiListener apiListener)
    {

    }

    @Override
    public void cancelReset()
    {

    }
}
