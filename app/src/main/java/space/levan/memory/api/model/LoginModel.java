package space.levan.memory.api.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.google.gson.Gson;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.ILoginModel;
import space.levan.memory.bean.BaseResponse;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class LoginModel implements ILoginModel {

    @Override
    public void userLogin(String username, String password, ApiListener listener)
    {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>()
        {
            @Override
            public void done(AVUser avUser, AVException e)
            {
                if (avUser != null)
                {
                    listener.onComplete("登录成功");
                }
                else
                {
                    listener.onFailed(new BaseResponse(new Gson().fromJson(e.getMessage(), BaseResponse.class).getError()));
                }
            }
        });
    }

    @Override
    public void cancelLogin()
    {
        //TODO: 取消登录
    }
}
