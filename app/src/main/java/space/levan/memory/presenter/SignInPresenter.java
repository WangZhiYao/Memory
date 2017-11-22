package space.levan.memory.presenter;

import javax.inject.Inject;

import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SignInContract;
import space.levan.memory.model.DataManager;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public class SignInPresenter extends RxPresenter<SignInContract.View> implements SignInContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SignInPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }
}
