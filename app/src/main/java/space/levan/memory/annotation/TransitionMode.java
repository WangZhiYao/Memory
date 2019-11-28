package space.levan.memory.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
@IntDef(value = {
        TransitionMode.TRANSITION_MODE_DEFAULT,
        TransitionMode.TRANSITION_MODE_LEFT,
        TransitionMode.TRANSITION_MODE_RIGHT
})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransitionMode {

    int TRANSITION_MODE_DEFAULT = 1;
    int TRANSITION_MODE_LEFT = 2;
    int TRANSITION_MODE_RIGHT = 3;
}
