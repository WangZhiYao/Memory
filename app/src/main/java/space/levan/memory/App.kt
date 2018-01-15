package space.levan.memory

import android.app.Application
import io.realm.Realm
import space.levan.memory.di.component.AppComponent
import space.levan.memory.di.component.DaggerAppComponent
import space.levan.memory.di.module.AppModule

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this)
    }

    companion object {

        var sAppComponent: AppComponent? = null
        var instance: App? = null
            private set

        val appComponent: AppComponent
            get() {
                if (sAppComponent == null) {
                    sAppComponent = DaggerAppComponent.builder()
                            .appModule(AppModule(instance!!))
                            .build()
                }

                return appComponent
            }
    }
}