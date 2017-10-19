package space.levan.memory.utils;

import java.util.List;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/19
 */

public class StringUtils {

    private static final String SEPARATOR = ",";

    public static String listToString(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            stringBuilder.append(str);
            stringBuilder.append(SEPARATOR);
        }

        String str = stringBuilder.toString();

        return str.substring(0, str.length() - SEPARATOR.length());
    }
}
