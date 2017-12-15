package space.levan.memory.presenter;

import javax.inject.Inject;

import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.ResetPasswordContract;
import space.levan.memory.model.DataManager;

/**
 * ResetPasswordPresenter
 *
 * @author WangZhiYao
 * @date 2017/12/15
 */

public class ResetPasswordPresenter extends RxPresenter<ResetPasswordContract.View> implements ResetPasswordContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ResetPasswordPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void resetPassword(String email) {

    }
}
