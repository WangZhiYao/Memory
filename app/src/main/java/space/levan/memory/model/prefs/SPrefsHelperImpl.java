package space.levan.memory.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import space.levan.memory.app.App;

/**
 * SharedPreferences interface implementation class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class SPrefsHelperImpl implements SPrefsHelper {

    private static final String SP_NAME = "Memory";
    private static final String SPLASH_PIC_PATH = "splash_pic_path";
    private static final String SIGN_UP_USER_EMAIL = "sign_up_user_email";
    private SharedPreferences mSPrefs;

    @Inject
    public SPrefsHelperImpl() {
        mSPrefs = App.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getSplashPicPath() {
        return mSPrefs.getString(SPLASH_PIC_PATH, "");
    }

    @Override
    public void setSplashPicPath(String picPath) {
        mSPrefs.edit().putString(SPLASH_PIC_PATH, picPath).apply();
    }

    @Override
    public String getUserEmail() {
        return mSPrefs.getString(SIGN_UP_USER_EMAIL, "");
    }

    @Override
    public void setUserEmail(String userEmail) {
        mSPrefs.edit().putString(SIGN_UP_USER_EMAIL, userEmail).apply();
    }
}
