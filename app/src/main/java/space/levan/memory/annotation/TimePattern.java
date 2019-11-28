package space.levan.memory.annotation;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
@StringDef(value = {TimePattern.DAY, TimePattern.MINUTE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimePattern {

    String DAY = "yyyy-MM-dd";
    String MINUTE = "yyyy-MM-dd HH:mm";
}
