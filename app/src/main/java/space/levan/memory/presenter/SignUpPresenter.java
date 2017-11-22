package space.levan.memory.presenter;

import javax.inject.Inject;

import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SignUpContract;
import space.levan.memory.model.DataManager;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public class SignUpPresenter extends RxPresenter<SignUpContract.View> implements SignUpContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SignUpPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }
}
