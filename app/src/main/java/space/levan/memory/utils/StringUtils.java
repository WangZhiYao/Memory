package space.levan.memory.utils;

import java.util.List;
import java.util.regex.Pattern;

import space.levan.memory.app.Constants;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/19
 */

public class StringUtils {

    private static final String SEPARATOR = ",";

    /**
     * list<String> 转换为String
     *
     * @param strings list
     * @return
     */
    public static String listToString(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            stringBuilder.append(str);
            stringBuilder.append(SEPARATOR);
        }

        String str = stringBuilder.toString();

        return str.substring(0, str.length() - SEPARATOR.length());
    }

    /**
     * 验证是否为邮箱
     *
     * @param email email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(Constants.REGEX_EMAIL, email);
    }
}
