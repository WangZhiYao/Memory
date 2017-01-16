package space.levan.memory.api.leancloud.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.google.gson.Gson;


import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.leancloud.model.impl.ILoginModel;
import space.levan.memory.bean.BaseResponse;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public class LoginModel implements ILoginModel {

    @Override
    public void userLogin(String username, String password, ApiCompleteListener listener)
    {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>()
        {
            @Override
            public void done(AVUser avUser, AVException e)
            {
                if (avUser != null)
                {
                    listener.onComplected("登录成功");
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
        // TODO: 2016-12-30 取消接口调用
    }
}
