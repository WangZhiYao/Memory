package space.levan.memory.di.component;

import android.app.Activity;

import dagger.Component;
import space.levan.memory.di.module.ActivityModule;
import space.levan.memory.di.scope.ActivityScope;
import space.levan.memory.ui.activities.MainActivity;
import space.levan.memory.ui.activities.ResetPasswordActivity;
import space.levan.memory.ui.activities.SearchActivity;
import space.levan.memory.ui.activities.SignInActivity;
import space.levan.memory.ui.activities.SignUpActivity;
import space.levan.memory.ui.activities.SplashActivity;

/**
 * Activity component
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    /**
     * get Activity
     *
     * @return
     */
    Activity getActivity();

    /**
     * binding MainActivity
     *
     * @param mainActivity MainActivity
     */
    void inject(MainActivity mainActivity);

    /**
     * binding SplashActivity
     *
     * @param splashActivity SplashActivity
     */
    void inject(SplashActivity splashActivity);

    /**
     * binding SignInActivity
     *
     * @param signInActivity SignInActivity
     */
    void inject(SignInActivity signInActivity);

    /**
     * binding SignUpActivity
     *
     * @param signUpActivity SignUpActivity
     */
    void inject(SignUpActivity signUpActivity);

    /**
     * binding SearchActivity
     *
     * @param searchActivity SearchActivity
     */
    void inject(SearchActivity searchActivity);

    /**
     * binding ResetPasswordActivity
     *
     * @param resetPasswordActivity ResetPasswordActivity
     */
    void inject(ResetPasswordActivity resetPasswordActivity);
}
