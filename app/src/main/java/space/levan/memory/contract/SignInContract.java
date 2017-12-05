package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * Contract for SignInPresenter
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public interface SignInContract {

    interface View extends BaseView {

        /**
         * set the email
         *
         * @param userEmail the email address that user use to sign in
         */
        void setUserEmail(String userEmail);

        /**
         * callback when sign in success
         */
        void signInSuccess();

        /**
         * callback when sign in failure
         *
         * @param msg error message
         */
        void signInFailure(String msg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * fetch the email address that user use to sign in
         */
        void fetchUserEmail();

        /**
         * user sign in
         *
         * @param userEmail email address
         * @param password  password
         */
        void userSignIn(String userEmail, String password);
    }
}
