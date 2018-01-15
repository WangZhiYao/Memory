package space.levan.memory.di.component

import dagger.Component
import space.levan.memory.App
import space.levan.memory.di.module.AppModule
import space.levan.memory.di.module.HttpModule
import javax.inject.Singleton

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, HttpModule::class))
interface AppComponent {

    var context: App
}