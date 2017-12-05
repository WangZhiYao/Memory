package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * Contract for SplashPresenter
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface SplashContract {

    interface View extends BaseView {

        /**
         * callback for save the splash image cached path
         *
         * @param picPath cached path
         */
        void setSplashData(String picPath);

        /**
         * jump to MainActivity
         */
        void jumpToMain();

        /**
         * jump to SignInActivity
         */
        void jumpToSignIn();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * fetch the splash image cached path
         */
        void fetchSplashData();

        /**
         * determine whether the user is logged in
         */
        void isUserSignIn();
    }
}
