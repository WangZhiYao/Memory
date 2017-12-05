package space.levan.memory.presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import javax.inject.Inject;

import space.levan.memory.app.App;
import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.MainContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.model.bean.project.Project;
import space.levan.memory.ui.adapters.MainAdapter;
import space.levan.memory.utils.RxUtils;

/**
 * MainPresenter
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getSplashData() {
        addSubscribe(mDataManager.getSplashData(App.SCREEN_WIDTH, App.SCREEN_HEIGHT)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(splashBean -> Glide.with(App.getInstance())
                        .load(splashBean.getUrls().getCustom())
                        .downloadOnly(new SimpleTarget<File>() {
                            @Override
                            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                                mDataManager.setSplashPicPath(resource.getAbsolutePath());
                            }
                        }), Throwable::printStackTrace));
    }

    @Override
    public void getAllProject() {
        if (mDataManager.getAllProject().isEmpty()) {
            mView.showEmptyView();
        } else {
            mView.showProject(mDataManager.getAllProject());
        }
    }

    @Override
    public void insertNewProject(Project project) {
        mDataManager.insertProject(project);
    }
}
