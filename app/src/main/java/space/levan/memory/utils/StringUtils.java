package space.levan.memory.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class StringUtils {

    public static SpannableString highlightKeywords(String keywords, String content) {

        SpannableString highlightContent = new SpannableString(content);

        if (!TextUtils.isEmpty(keywords)) {

            for (char c : keywords.toCharArray()) {

                Pattern p = Pattern.compile(String.valueOf(c));
                Matcher m = p.matcher(content);

                while (m.find()) {
                    int start = m.start();
                    int end = m.end();

                    highlightContent.setSpan(new ForegroundColorSpan(Color.RED),
                            start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        return highlightContent;
    }

    @NonNull
    public static String nullThenEmpty(@Nullable String value) {
        return value == null ? "" : value;
    }
}
