package space.levan.memory.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import space.levan.memory.app.App;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class SPrefsHelperImpl implements SPrefsHelper {

    private static final String SP_NAME = "Memory";
    private SharedPreferences mSPrefs;

    @Inject
    public SPrefsHelperImpl() {
        mSPrefs = App.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
}
