package space.levan.memory.app;

import android.os.Environment;

import java.io.File;

/**
 * Constants class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class Constants {

    /**
     * LeanCloud app id
     */
    public static final String LC_APPID = "D6pk8zwWuMB89hBIbB99oJQf-gzGzoHsz";

    /**
     * LeanCloud app key
     */
    public static final String LC_APPKEY = "IAPARDj6aNQ3YDQ88QydyvAn";

    /**
     * path for data
     */
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath()
            + File.separator + "data";

    /**
     * path for net cache
     */
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * path for sdcard
     */
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "levan" + File.separator + "Memory";

    /**
     * RegEx for email
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
}
