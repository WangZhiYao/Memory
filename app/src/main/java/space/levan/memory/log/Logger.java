package space.levan.memory.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class Logger {

    private static final String TAG = "Memory";


    public static void v(@NonNull String tag, @NonNull String msg) {
        log(Log.VERBOSE, tag, msg);
    }

    public static void d(@NonNull String tag, @NonNull String msg) {
        log(Log.DEBUG, tag, msg);
    }

    public static void i(@NonNull String tag, @NonNull String msg) {
        log(Log.INFO, tag, msg);
    }

    public static void w(@NonNull String tag, @NonNull String msg) {
        log(Log.WARN, tag, msg);
    }

    public static void e(@NonNull String tag, @NonNull String msg) {
        log(Log.ERROR, tag, msg);
    }

    public static void e(@NonNull String tag, @NonNull Throwable ex) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            log(Log.ERROR, tag, sw.toString());
        } catch (Exception e) {
            log(Log.ASSERT, TAG, ex.toString());
        }
    }

    private static void log(int level, String tag, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(tag);

        try {
            Exception ex = new Exception();
            StackTraceElement stackTrace = ex.getStackTrace()[2];
            String methodName = stackTrace.getMethodName();
            sb.append(".").append(methodName);
        } catch (Exception ex) {
            log(Log.ASSERT, TAG, ex.toString());
        } finally {
            sb.append("]").append(" ").append(msg);
        }

        switch (level) {
            case Log.VERBOSE:
                Log.v(TAG, sb.toString());
                break;
            case Log.DEBUG:
                Log.d(TAG, sb.toString());
                break;
            case Log.INFO:
                Log.i(TAG, sb.toString());
                break;
            case Log.WARN:
                Log.w(TAG, sb.toString());
                break;
            case Log.ERROR:
                Log.e(TAG, sb.toString());
                break;
            case Log.ASSERT:
            default:
                Log.wtf(TAG, sb.toString());
                break;
        }
    }
}
