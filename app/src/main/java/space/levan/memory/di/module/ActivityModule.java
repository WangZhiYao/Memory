package space.levan.memory.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import space.levan.memory.di.scope.ActivityScope;

/**
 * Activity Module
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
