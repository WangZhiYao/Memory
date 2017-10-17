package space.levan.memory.model;

import space.levan.memory.model.db.RealmHelper;
import space.levan.memory.model.http.HttpHelper;
import space.levan.memory.model.prefs.SPrefsHelper;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class DataManager implements RealmHelper, HttpHelper, SPrefsHelper {

    RealmHelper mRealmHelper;
    HttpHelper mHttpHelper;
    SPrefsHelper mSPrefsHelper;

    public DataManager(RealmHelper realmHelper, HttpHelper httpHelper, SPrefsHelper sPrefsHelper) {
        this.mRealmHelper = realmHelper;
        this.mHttpHelper = httpHelper;
        this.mSPrefsHelper = sPrefsHelper;
    }
}
