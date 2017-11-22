package space.levan.memory.di.component;

import android.app.Activity;

import dagger.Component;
import space.levan.memory.di.module.ActivityModule;
import space.levan.memory.di.scope.ActivityScope;
import space.levan.memory.ui.activities.MainActivity;
import space.levan.memory.ui.activities.SignInActivity;
import space.levan.memory.ui.activities.SignUpActivity;
import space.levan.memory.ui.activities.SplashActivity;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(SignInActivity signInActivity);

    void inject(SignUpActivity signUpActivity);
}
