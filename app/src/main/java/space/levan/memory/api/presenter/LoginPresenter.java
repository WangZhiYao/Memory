package space.levan.memory.api.presenter;

import android.text.TextUtils;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.LoginModel;
import space.levan.memory.api.model.impl.ILoginModel;
import space.levan.memory.api.presenter.impl.ILoginPresenter;
import space.levan.memory.api.view.ILoginView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.utils.NetworkUtils;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class LoginPresenter implements ILoginPresenter, ApiListener {

    private ILoginModel mILoginModel;
    private ILoginView mILoginView;
    private static final int SHAKE_USERNAME              = 1;
    private static final int SHAKE_PASSWORD              = 2;

    public LoginPresenter(ILoginView view)
    {
        mILoginView = view;
        mILoginModel = new LoginModel();
    }

    @Override
    public void userLogin(String username, String password)
    {
        if (!NetworkUtils.isConnected(App.getApplication()))
        {
            mILoginView.showMessage(App.getApplication().getResources().getString(R.string.poor_network));
            return;
        }
        if (TextUtils.isEmpty(username))
        {
            mILoginView.shake(SHAKE_USERNAME);
            mILoginView.showMessage(App.getApplication().getString(R.string.login_empty_username));
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            mILoginView.shake(SHAKE_PASSWORD);
            mILoginView.showMessage(App.getApplication().getString(R.string.login_empty_password));
            return;
        }
        mILoginView.showProgress();
        mILoginModel.userLogin(username, password, this);
    }

    @Override
    public void cancelLogin()
    {
        mILoginView.hideProgress();
        mILoginModel.cancelLogin();
    }

    @Override
    public void onComplete(Object result)
    {
        mILoginView.hideProgress();
        mILoginView.loginSuccess(result.toString());
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mILoginView.hideProgress();
        mILoginView.showMessage(msg.getError());
    }
}
