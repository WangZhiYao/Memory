package space.levan.memory.presenter;

import com.avos.avoscloud.AVUser;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SplashContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.utils.RxUtils;

/**
 * SplashPresenter
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {

    private static final int COUNT_DOWN_TIME = 3500;

    private DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void fetchSplashData() {
        mView.setSplashData(mDataManager.getSplashPicPath());
    }

    @Override
    public void isUserSignIn() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(aLong -> {
                    AVUser user = AVUser.getCurrentUser();
                    if (user != null) {
                        mView.jumpToMain();
                    } else {
                        mView.jumpToSignIn();
                    }
                }));
    }
}
