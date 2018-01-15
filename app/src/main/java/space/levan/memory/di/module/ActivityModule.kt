package space.levan.memory.di.module

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import space.levan.memory.di.scope.ActivityScope

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity {
        return activity
    }
}