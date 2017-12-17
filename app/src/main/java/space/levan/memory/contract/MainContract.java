package space.levan.memory.contract;

import java.util.List;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;
import space.levan.memory.model.bean.project.Project;

/**
 * Contract for MainPresenter
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface MainContract {

    interface View extends BaseView {

        /**
         * callback when get project list success
         *
         * @param projects project list
         */
        void showProject(List<Project> projects);

        /**
         * callback when user sign out
         */
        void jumpToSignIn();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * get the splash image from UnSplash.com for next time start this app
         */
        void getSplashData();

        /**
         * get project
         */
        void getAllProject();

        /**
         * create a new project
         *
         * @param project project
         */
        void insertNewProject(Project project);

        /**
         * user sign out
         */
        void userSignOut();
    }
}
