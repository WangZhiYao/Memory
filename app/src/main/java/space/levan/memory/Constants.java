package space.levan.memory;

import android.Manifest;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class Constants {

    public static final String DB_NAME = "Memory.db";

    public static final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static final int REQUEST_CODE_PERMISSIONS = 0x0001;
    public static final int REQUEST_CODE_SEARCH_BOOK = 0x0002;

    public static final int REQUEST_CODE_IMAGE_SELECTOR = 9603;

    public static final String DOUBAN_API_KEY = "0dad551ec0f84ed02907ff5c42e8ec70";

    /**
     * 传参Key - Activity 切换方式
     */
    public static final String EXTRA_TRANSITION_MODE = "TRANSITION_MODE";

    /**
     * 传参Key - 书籍信息
     */
    public static final String EXTRA_BOOK = "BOOK";
}
