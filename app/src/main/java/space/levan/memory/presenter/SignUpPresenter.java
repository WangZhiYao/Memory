package space.levan.memory.presenter;

import javax.inject.Inject;

import io.realm.ObjectServerError;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import space.levan.memory.app.Constants;
import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SignUpContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.utils.StringUtils;

/**
 * SignUpPresenter
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

    @Override
    public void userSignUp(String userEmail, String password) {
        if (StringUtils.isEmail(userEmail)) {
            SyncCredentials userCredentials = SyncCredentials.usernamePassword(userEmail, password, true);
            SyncUser.loginAsync(userCredentials, Constants.REALM_AUTH_URL, new SyncUser.Callback<SyncUser>() {
                @Override
                public void onSuccess(SyncUser result) {
                    mView.signUpSuccess();
                }

                @Override
                public void onError(ObjectServerError error) {
                    mView.signUpFailure(error.getErrorMessage());
                }
            });
        } else {
            mView.showMessage("请输入正确的邮箱地址");
        }

    }
}
