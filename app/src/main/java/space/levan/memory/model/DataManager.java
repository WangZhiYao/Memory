package space.levan.memory.model;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.douban.BookResultBean;
import space.levan.memory.model.bean.splash.SplashBean;
import space.levan.memory.model.db.DBHelper;
import space.levan.memory.model.http.HttpHelper;
import space.levan.memory.model.prefs.SPrefsHelper;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class DataManager implements DBHelper, HttpHelper, SPrefsHelper {

    private DBHelper mDBHelper;
    private HttpHelper mHttpHelper;
    private SPrefsHelper mSPrefsHelper;

    public DataManager(DBHelper DBHelper, HttpHelper httpHelper, SPrefsHelper sPrefsHelper) {
        this.mDBHelper = DBHelper;
        this.mHttpHelper = httpHelper;
        this.mSPrefsHelper = sPrefsHelper;
    }

    @Override
    public Flowable<SplashBean> getSplashData(int scrWidth, int scrHeight) {
        return mHttpHelper.getSplashData(scrWidth, scrHeight);
    }

    @Override
    public Flowable<BookResultBean> getBookData(String q, int start, int count) {
        return mHttpHelper.getBookData(q, start, count);
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
