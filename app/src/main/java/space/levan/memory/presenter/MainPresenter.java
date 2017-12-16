package space.levan.memory.presenter;

import android.util.Log;

import com.avos.avoscloud.AVUser;

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
        addSubscribe(mDataManager.getSplashData(App.SCREEN_WIDTH, App.SCREEN_HEIGHT)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<Splash>() {
                    @Override
                    public void accept(Splash splash) throws Exception {
                        Log.w("WZY", splash.toString());
                    }
                }));

        // FIXME: 2017/12/17 Can not add error handler
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

    @Override
    public void userSignOut() {
        AVUser.logOut();
        mView.jumpToSignIn();
    }
}
