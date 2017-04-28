package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.LoginModel;
import space.levan.memory.api.model.impl.ILoginModel;
import space.levan.memory.api.presenter.impl.ILoginPresenter;
import space.levan.memory.api.view.ILoginView;
import space.levan.memory.bean.douban.BaseResponse;

/**
 * Created by WangZhiYao on 2017/4/13.
 */

public class LoginPresenter implements ILoginPresenter, ApiListener
{
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
        mILoginView.loginSuccess();
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mILoginView.hideProgress();
        mILoginView.showMessage(msg.getMsg());
    }
}
