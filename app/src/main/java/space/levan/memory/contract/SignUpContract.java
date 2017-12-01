package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * 注册契约类
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public interface SignUpContract {

    interface View extends BaseView {

        /**
         * 注册成功
         */
        void signUpSuccess();

        /**
         * 注册失败
         *
         * @param msg 失败消息
         */
        void signUpFailure(String msg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 用户注册
         *
         * @param userEmail 邮箱
         * @param password  密码
         */
        void userSignUp(String userEmail, String password);
    }
}
