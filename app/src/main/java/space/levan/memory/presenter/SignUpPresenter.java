package space.levan.memory.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

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

    @Override
    public void userSignUp(String username, String userEmail, String password) {
        AVUser user = new AVUser();
        user.setEmail(userEmail);
        user.setPassword(password);
        user.setUsername(userEmail);
        user.add("nickname", username);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {

            }
        });
    }
}
