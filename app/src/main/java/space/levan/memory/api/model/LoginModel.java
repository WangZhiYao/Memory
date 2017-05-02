package space.levan.memory.api.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

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
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>()
        {
            @Override
            public void done(AVUser avUser, AVException e)
            {
                listener.onComplete(null);
            }
        });
    }

    @Override
    public void cancelLogin()
    {

    }
}
