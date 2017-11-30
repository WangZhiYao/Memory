package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * 登录契约类
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public interface SignInContract {

    interface View extends BaseView {

        /**
         * 设置登录的邮箱
         *
         * @param userEmail 注册的邮箱
         */
        void setUserEmail(String userEmail);

        /**
         * 登录成功
         */
        void signInSuccess();

        /**
         * 登录失败
         *
         * @param msg 失败消息
         */
        void signInFailure(String msg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 从本地查找用户注册时的邮箱
         */
        void fetchUserEmail();

        /**
         * 用户登录
         *
         * @param userEmail 邮箱
         * @param password  密码
         */
        void userSignIn(String userEmail, String password);
    }
}
