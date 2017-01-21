package space.levan.memory;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.avos.avoscloud.AVOSCloud;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import space.levan.memory.common.Constant;
import space.levan.memory.view.activity.BaseActivity;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class App extends Application {

    public final static String TAG = "App";
    public final static boolean DEBUG = true;
    private static App application;
    private static int mainTid;
    /**
     * Activity集合，来管理所有的Activity
     */
    private static List<BaseActivity> activities;

    static
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        activities = new LinkedList<>();
        application = this;
        mainTid = android.os.Process.myTid();
        initLeanCloud();
        initRealm();
    }

    private void initLeanCloud()
    {
        AVOSCloud.initialize(this, Constant.APP_ID, Constant.APP_KEY);
    }

    private void initRealm()
    {
        Realm.init(this);
        RealmConfiguration config = new  RealmConfiguration.Builder()
                .name("Book.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * 获取application
     *
     * @return
     */
    public static Context getApplication()
    {
        return application;
    }

    /**
     * 获取主线程ID
     *
     * @return
     */
    public static int getMainTid()
    {
        return mainTid;
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity)
    {
        activities.add(activity);
    }

    /**
     * 结束一个Activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity)
    {
        activities.remove(activity);
    }

    /**
     * 结束当前所有Activity
     */
    public static void clearActivities()
    {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext())
        {
            activity = iterator.next();
            if (activity != null)
            {
                activity.finish();
            }
        }
    }

    /**
     * 退出应运程序
     */
    public static void quiteApplication()
    {
        clearActivities();
        System.exit(0);
    }
}
