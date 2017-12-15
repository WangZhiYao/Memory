package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * Contract for ResetPassword Presenter
 *
 * @author WangZhiYao
 * @date 2017/12/15
 */

public interface ResetPasswordContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * send a reset password email to user email address
         *
         * @param email email address
         */
        void resetPassword(String email);
    }
}
