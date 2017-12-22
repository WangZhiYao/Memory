package space.levan.memory.presenter;

import com.avos.avoscloud.AVUser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import space.levan.memory.app.App;
import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.MainContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.model.bean.project.Project;
import space.levan.memory.model.bean.splash.Splash;
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

        // FIXME: 2017/12/17 Can not add error handler
        /*
        addSubscribe(mDataManager.getSplashData(App.SCREEN_WIDTH, App.SCREEN_HEIGHT)
                .compose(RxUtils.<UnSplashResponse<Splash>>rxSchedulerHelper())
                .compose(RxUtils.<Splash>handleUnSplashResult())
                .subscribeWith(new SubscriberUtils<Splash>(mView, "下载启动图片失败") {
                    @Override
                    public void onNext(Splash splash) {
                        Log.w("WZY", splash.getUrls().getCustom());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w("WZY", t.getLocalizedMessage());
                    }
                }));*/

        addSubscribe(mDataManager.getSplashData(App.SCREEN_WIDTH, App.SCREEN_HEIGHT)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<Splash>() {
                    @Override
                    public void accept(Splash splash) throws Exception {
                        Glide.with(App.getInstance())
                                .load(splash.getUrls().getCustom())
                                .downloadOnly(new SimpleTarget<File>() {
                                    @Override
                                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                                        mDataManager.setSplashPicPath(resource.getAbsolutePath());
                                    }
                                });
                    }
                }));
    }

    @Override
    public void getAllProject() {
        mView.showContent(mDataManager.getAllProject());
    }

    @Override
    public void insertNewProject(Project project) {
        mDataManager.insertProject(project);
    }

    @Override
    public void userSignOut() {
        AVUser.logOut();
        mView.jumpToSignIn();
    }
}
