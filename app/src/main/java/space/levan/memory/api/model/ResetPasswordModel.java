package space.levan.memory.api.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IResetPasswordModel;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class ResetPasswordModel implements IResetPasswordModel
{
    @Override
    public void userResetPwd(String email, ApiListener listener)
    {
        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback()
        {
            @Override
            public void done(AVException e)
            {
                listener.onComplete(e.getMessage());
            }
        });
    }

    @Override
    public void cancelReset()
    {

    }
}
