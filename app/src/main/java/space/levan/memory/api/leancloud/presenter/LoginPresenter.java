package space.levan.memory.api.leancloud.presenter;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.leancloud.model.impl.ILoginModel;
import space.levan.memory.api.leancloud.model.LoginModel;
import space.levan.memory.api.leancloud.presenter.impl.ILoginPresenter;
import space.levan.memory.api.leancloud.view.ILoginView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.utils.NetworkUtils;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public class LoginPresenter implements ILoginPresenter, ApiCompleteListener {

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
        if (username.equals("") || password.equals(""))
        {
            mILoginView.showMessage(App.getApplication().getResources().getString(R.string.login_empty_username_or_password));
            return;
        }
        mILoginView.showProgress();
        mILoginModel.userLogin(username, password, this);
    }

    @Override
    public void cancelLogin()
    {
        mILoginModel.cancelLogin();
    }

    @Override
    public void onComplected(Object result)
    {
        mILoginView.hideProgress();
        mILoginView.loginSuccess(result.toString());
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mILoginView.hideProgress();
        mILoginView.loginFailed(msg.getError());
    }
}
