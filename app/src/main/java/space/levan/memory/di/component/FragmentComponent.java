package space.levan.memory.di.component;

import android.app.Activity;

import dagger.Component;
import space.levan.memory.di.module.FragmentModule;
import space.levan.memory.di.scope.FragmentScope;

/**
 * Fragment component
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    /**
     * get Activity
     *
     * @return
     */
    Activity getActivity();
}
