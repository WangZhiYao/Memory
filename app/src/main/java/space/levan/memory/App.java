package space.levan.memory;

import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.log.LogLevel;
import io.realm.log.RealmLog;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class App extends Application {
    private static App mApp;
    private static int mainTid;
    private static List<BaseActivity> activities;

    public static String AUTH_URL = "http://" + BuildConfig.SERVER_URL + ":9080/auth";
    public static String REALM_URL = "realm://" + BuildConfig.SERVER_URL + ":9080/~/Memory";

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        activities = new LinkedList<>();
        mainTid = android.os.Process.myTid();
        Realm.init(this);
        RealmLog.setLevel(LogLevel.TRACE);
    }

    /**
     * 获取application
     *
     * @return
     */
    public static Context getApplication() {
        return mApp;
    }

    /**
     * 获取主线程ID
     *
     * @return
     */
    public static int getMainTid() {
        return mainTid;
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    /**
     * 结束一个Activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    /**
     * 结束当前所有Activity
     */
    public static void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应运程序
     */
    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }
}
