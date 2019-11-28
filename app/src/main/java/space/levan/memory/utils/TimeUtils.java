package space.levan.memory.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import space.levan.memory.annotation.TimePattern;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class TimeUtils {

    public static String timestampToDateString(@TimePattern String pattern, long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}
