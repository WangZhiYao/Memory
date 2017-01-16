package space.levan.memory.api.leancloud.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.google.gson.Gson;

import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.leancloud.model.impl.IRegisterModel;
import space.levan.memory.bean.BaseResponse;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public class RegisterModel implements IRegisterModel {

    @Override
    public void userRegister(String nickname, String username, String password, ApiCompleteListener listener)
    {
        AVUser user = new AVUser();
        user.setUsername(username);
        user.setEmail(username);
        user.setPassword(password);
        user.put("nickname", nickname);
        user.signUpInBackground(new SignUpCallback()
        {
            @Override
            public void done(AVException e)
            {
                if (e == null)
                {
                    listener.onComplected("注册成功！");
                }
                else
                {
                    listener.onFailed(new BaseResponse(new Gson().fromJson(e.getMessage(), BaseResponse.class).getError()));
                }
            }
        });

    }

    @Override
    public void cancelRegister()
    {
        //TODO 2016-12-30 取消接口调用
    }
}
