package space.levan.memory.db.converters;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class ListConverter {

    @Nullable
    @TypeConverter
    public static String listToString(@Nullable List<String> value) {
        if (value != null && !value.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String str : value) {
                sb.append(str).append(",");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.lastIndexOf(","));
            }

            return sb.toString();
        }

        return null;
    }

    @Nullable
    @TypeConverter
    public static List<String> stringToList(@Nullable String value) {
        if (!TextUtils.isEmpty(value)) {
            return Arrays.asList(value.split(","));
        }

        return null;
    }
}
