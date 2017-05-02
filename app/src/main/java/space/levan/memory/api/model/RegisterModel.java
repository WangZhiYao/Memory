package space.levan.memory.api.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IRegisterModel;
import space.levan.memory.bean.douban.BaseResponse;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class RegisterModel implements IRegisterModel
{
    @Override
    public void userRegister(String nickname, String email, String username, String password, ApiListener listener)
    {
        AVUser avUser = new AVUser();
        avUser.setUsername(email);
        avUser.setPassword(password);
        avUser.setEmail(email);
        avUser.add("nickname", nickname);
        avUser.signUpInBackground(new SignUpCallback()
        {
            @Override
            public void done(AVException e)
            {
                if (e == null)
                {
                    listener.onComplete("");
                }
                else
                {
                    listener.onFailed(new BaseResponse(e.getCode(), e.getMessage()));
                }
            }
        });
    }

    @Override
    public void cancelRegister()
    {

    }
}
