package space.levan.memory.api.model;

import io.realm.ObjectServerError;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import space.levan.memory.BuildConfig;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IRegisterModel;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class RegisterModel implements IRegisterModel
{
    @Override
    public void userRegister(String email, String password, ApiListener apiListener)
    {
        SyncCredentials syncCredentials = SyncCredentials.usernamePassword(email, password, true);
        SyncUser.loginAsync(syncCredentials, BuildConfig.REALM_AUTH_URL, new SyncUser.Callback()
        {
            @Override
            public void onSuccess(SyncUser user)
            {
                apiListener.onSuccess(user);
            }

            @Override
            public void onError(ObjectServerError error)
            {
                apiListener.onFailure(error.getErrorCode().toString());
            }
        });
    }

    @Override
    public void cancelRegister()
    {

    }
}
