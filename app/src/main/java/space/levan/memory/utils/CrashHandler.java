package space.levan.memory.utils;

import org.jetbrains.annotations.NotNull;

import space.levan.memory.log.Logger;

/**
 * @author WangZhiYao
 * @date 2019/7/22
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private CrashHandler() {

    }

    private static class SingletonHolder {
        private static CrashHandler mInstance = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return SingletonHolder.mInstance;
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * uncaughtException 回调函数
     */
    @Override
    public void uncaughtException(@NotNull Thread thread, @NotNull Throwable ex) {
        Logger.e(TAG, ex);
    }
}
