package space.levan.memory.api.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.google.gson.Gson;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IResetPasswordModel;
import space.levan.memory.bean.BaseResponse;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class ResetPasswordModel implements IResetPasswordModel {

    @Override
    public void resetPassword(String email, ApiListener listener)
    {
        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback()
        {
            @Override
            public void done(AVException e)
            {
                if (e == null)
                {
                    listener.onComplete("重置密码邮件已经发送到邮箱，请查看");
                }
                else
                {
                    listener.onFailed(new BaseResponse(new Gson().fromJson(e.getMessage(), BaseResponse.class).getError()));
                }
            }
        });
    }
}
