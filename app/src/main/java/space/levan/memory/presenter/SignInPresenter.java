package space.levan.memory.presenter;

import android.text.TextUtils;

import javax.inject.Inject;

import io.realm.ObjectServerError;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import space.levan.memory.app.Constants;
import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SignInContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.utils.StringUtils;

/**
 * SignInPresenter
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

    @Override
    public void fetchUserEmail() {
        String userEmail = mDataManager.getUserEmail();
        if (!TextUtils.equals(userEmail, "")) {
            mView.setUserEmail(userEmail);
        }
    }

    @Override
    public void userSignIn(String userEmail, String password) {
        if (StringUtils.isEmail(userEmail)) {
            if (!TextUtils.isEmpty(password)) {
                SyncCredentials userCredentials = SyncCredentials.usernamePassword(userEmail, password, false);
                SyncUser.loginAsync(userCredentials, Constants.REALM_AUTH_URL, new SyncUser.Callback<SyncUser>() {
                    @Override
                    public void onSuccess(SyncUser result) {
                        mView.signInSuccess();
                    }

                    @Override
                    public void onError(ObjectServerError error) {
                        mView.signInFailure(error.getErrorMessage());
                    }
                });
            } else {
                mView.showMessage("密码不能为空！");
            }
        } else {
            mView.showMessage("请输入正确的邮箱地址");
        }
    }
}
