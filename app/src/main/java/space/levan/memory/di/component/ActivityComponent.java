package space.levan.memory.di.component;

import android.app.Activity;

import dagger.Component;
import space.levan.memory.di.module.ActivityModule;
import space.levan.memory.di.scope.ActivityScope;
import space.levan.memory.ui.activities.MainActivity;
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

    void Inject(MainActivity mainActivity);

    void Inject(SplashActivity splashActivity);
}
