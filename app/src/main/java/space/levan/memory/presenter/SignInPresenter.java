package space.levan.memory.presenter;

import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import javax.inject.Inject;

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
                AVUser.logInInBackground(userEmail, password, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            mView.signInSuccess();
                        } else {
                            mView.signInFailure(e.getMessage());
                        }
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
