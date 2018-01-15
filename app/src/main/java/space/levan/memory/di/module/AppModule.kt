package space.levan.memory.di.module

import dagger.Module
import dagger.Provides
import space.levan.memory.App
import javax.inject.Singleton

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    internal fun provideApp(): App {
        return app
    }
}