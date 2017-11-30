package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * 启动界面契约类
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface SplashContract {

    interface View extends BaseView {

        /**
         * 返回图片的缓存路径
         *
         * @param picPath 图片的缓存路径
         */
        void setSplashData(String picPath);

        /**
         * 跳转到主界面
         */
        void jumpToMain();

        /**
         * 跳转到登录界面
         */
        void jumpToSignIn();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 查找缓存的图片路径
         */
        void fetchSplashData();

        /**
         * 判断用户是否登录
         */
        void isUserSignIn();
    }
}
