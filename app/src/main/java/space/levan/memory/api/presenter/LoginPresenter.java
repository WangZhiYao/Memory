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
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            mILoginView.showMessage(App.getApplication().getResources().getString(R.string.empty_username_or_password));
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
