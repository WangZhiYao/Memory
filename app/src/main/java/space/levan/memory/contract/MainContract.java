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

        void getSplashData();
    }
}