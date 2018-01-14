package space.levan.memory.app;

import android.os.Environment;

import java.io.File;

/**
 * Constants class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface Constants {

    /**
     * path for data
     */
    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath()
            + File.separator + "data";

    /**
     * path for net cache
     */
    String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * path for sdcard
     */
    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "levan" + File.separator + "Memory";

    /**
     * RegEx for email
     */
    String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * Realm Auth
     */
    String REALM_AUTH_URL = "http://120.79.183.144:9080/auth";
}
