package space.levan.memory.contract;

import java.util.List;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;
import space.levan.memory.model.bean.project.Project;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface MainContract {

    interface View extends BaseView {

        /**
         * 返回项目列表
         *
         * @param projects
         */
        void showProject(List<Project> projects);

        /**
         * 没有项目时的空视图
         */
        void showEmptyView();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 从网上去获取下次启动界面的图片地址
         */
        void getSplashData();

        /**
         * 获取当前项目
         */
        void getAllProject();
    }
}
