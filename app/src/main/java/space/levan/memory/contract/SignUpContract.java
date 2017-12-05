package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * Contract for SignUpPresenter
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public interface SignUpContract {

    interface View extends BaseView {

        /**
         * callback when user sign up success
         */
        void signUpSuccess();

        /**
         * callback when user sign up failure
         *
         * @param msg error message
         */
        void signUpFailure(String msg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * user sign up
         *
         * @param userEmail user email address
         * @param password  password
         */
        void userSignUp(String userEmail, String password);
    }
}
