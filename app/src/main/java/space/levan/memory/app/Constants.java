package space.levan.memory.app;

import android.os.Environment;

import java.io.File;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class Constants {

    public static final String LC_APPID = "D6pk8zwWuMB89hBIbB99oJQf-gzGzoHsz";

    public static final String LC_APPKEY = "IAPARDj6aNQ3YDQ88QydyvAn";

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath()
            + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "levan" + File.separator + "Memory";
}
