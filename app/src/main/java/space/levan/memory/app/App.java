package space.levan.memory.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.avos.avoscloud.AVOSCloud;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;
import space.levan.memory.di.component.AppComponent;
import space.levan.memory.di.component.DaggerAppComponent;
import space.levan.memory.di.module.AppModule;

/**
 * Custom Application class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class App extends Application {

    public static AppComponent sAppComponent;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    private static App mApp;
    private Set<Activity> mAllActivities;

    public static synchronized App getInstance() {
        return mApp;
    }

    public static AppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mApp))
                    .build();
        }

        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getScreenSize();
        mApp = this;
        Realm.init(this);
        initLeanCloud();
    }

    private void initLeanCloud() {
        AVOSCloud.initialize(this, Constants.LC_APPID, Constants.LC_APPKEY);
    }

    public void addActivity(Activity act) {
        if (mAllActivities == null) {
            mAllActivities = new HashSet<>();
        }
        mAllActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (mAllActivities != null) {
            mAllActivities.remove(act);
        }
    }

    public void getScreenSize() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
        }
        SCREEN_WIDTH = outMetrics.widthPixels;
        SCREEN_HEIGHT = outMetrics.heightPixels;
    }

    public void exitApp() {
        if (mAllActivities != null) {
            synchronized (mAllActivities) {
                for (Activity mAllActivity : mAllActivities) {
                    mAllActivity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
