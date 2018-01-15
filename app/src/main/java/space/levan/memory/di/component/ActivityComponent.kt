package space.levan.memory.di.component

import android.support.v7.app.AppCompatActivity
import dagger.Component
import space.levan.memory.di.module.ActivityModule
import space.levan.memory.di.scope.ActivityScope

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    var activity: AppCompatActivity
}