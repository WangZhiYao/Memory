package space.levan.memory.contract;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface MainContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 从网上去获取下次启动界面的图片地址
         */
        void getSplashData();
    }
}
