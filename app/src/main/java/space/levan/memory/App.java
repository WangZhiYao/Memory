package space.levan.memory;

import android.app.Application;

import space.levan.memory.utils.CrashHandler;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        CrashHandler.getInstance().init();
    }

    public static App getInstance() {
        return mInstance;
    }
}
