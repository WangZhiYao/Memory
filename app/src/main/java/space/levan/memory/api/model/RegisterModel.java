package space.levan.memory.api.model;

import android.util.Log;

import io.realm.ObjectServerError;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import space.levan.memory.BuildConfig;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IRegisterModel;
import space.levan.memory.utils.RealmUtils;

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
                Log.w("WZY", error.toString());
                apiListener.onFailure(error.toString());
            }
        });
    }

    @Override
    public void cancelRegister()
    {

    }
}
