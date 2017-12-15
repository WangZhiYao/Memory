package space.levan.memory.model;

import java.util.List;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.model.bean.douban.Books;
import space.levan.memory.model.bean.project.Project;
import space.levan.memory.model.bean.splash.Splash;
import space.levan.memory.model.db.DBHelper;
import space.levan.memory.model.http.HttpHelper;
import space.levan.memory.model.http.response.DouBanResponse;
import space.levan.memory.model.http.response.UnSplashResponse;
import space.levan.memory.model.prefs.SPrefsHelper;

/**
 * DataManager
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class DataManager implements DBHelper, HttpHelper, SPrefsHelper {

    private DBHelper mDBHelper;
    private HttpHelper mHttpHelper;
    private SPrefsHelper mSPrefsHelper;

    public DataManager(DBHelper dbHelper, HttpHelper httpHelper, SPrefsHelper sPrefsHelper) {
        this.mDBHelper = dbHelper;
        this.mHttpHelper = httpHelper;
        this.mSPrefsHelper = sPrefsHelper;
    }

    @Override
    public Flowable<UnSplashResponse<Splash>> getSplashData(int scrWidth, int scrHeight) {
        return mHttpHelper.getSplashData(scrWidth, scrHeight);
    }

    @Override
    public Flowable<DouBanResponse<BookResult>> getBookData(String q, int start, int count) {
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

    @Override
    public String getUserEmail() {
        return mSPrefsHelper.getUserEmail();
    }

    @Override
    public void setUserEmail(String userEmail) {
        mSPrefsHelper.setUserEmail(userEmail);
    }

    @Override
    public void insertProject(Project project) {
        mDBHelper.insertProject(project);
    }

    @Override
    public List<Project> getAllProject() {
        return mDBHelper.getAllProject();
    }

    @Override
    public void insertBook(Books books) {
        mDBHelper.insertBook(books);
    }
}
