package space.levan.memory.utils;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;

import space.levan.memory.log.Logger;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    public static File getPrivateFileDir(Context context) {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && context.getExternalFilesDir(null) != null
                ? context.getExternalFilesDir(null) : context.getFilesDir();
    }

    public static File getPrivateCacheDir(Context context) {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && context.getExternalCacheDir() != null
                ? context.getExternalCacheDir() : context.getCacheDir();
    }

    public static boolean deleteFile(@NonNull String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            try {
                return file.delete();
            } catch (Exception ex) {
                Logger.e(TAG, ex);
            }
        }

        return false;
    }
}
