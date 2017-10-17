package space.levan.memory.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface SplashUrl {
}
