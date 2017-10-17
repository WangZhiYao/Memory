package space.levan.memory.model;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.SplashBean;
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

    private RealmHelper mRealmHelper;
    private HttpHelper mHttpHelper;
    private SPrefsHelper mSPrefsHelper;

    public DataManager(RealmHelper realmHelper, HttpHelper httpHelper, SPrefsHelper sPrefsHelper) {
        this.mRealmHelper = realmHelper;
        this.mHttpHelper = httpHelper;
        this.mSPrefsHelper = sPrefsHelper;
    }

    @Override
    public Flowable<SplashBean> getSplashData(int scrWidth, int scrHeight) {
        return mHttpHelper.getSplashData(scrWidth, scrHeight);
    }

    @Override
    public String getSplashPicPath() {
        return mSPrefsHelper.getSplashPicPath();
    }

    @Override
    public void setSplashPicPath(String picPath) {
        mSPrefsHelper.setSplashPicPath(picPath);
    }
}
