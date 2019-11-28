package space.levan.memory.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class DeviceUtils {

    public static boolean hasFlashLight(Context context) {
        return context.getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
}
