package space.levan.memory.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
