package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.RegisterModel;
import space.levan.memory.api.model.impl.IRegisterModel;
import space.levan.memory.api.presenter.impl.IRegisterPresenter;
import space.levan.memory.api.view.IRegisterView;
import space.levan.memory.bean.douban.BaseResponse;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class RegisterPresenter implements IRegisterPresenter, ApiListener
{
    private IRegisterModel mIRegisterModel;
    private IRegisterView mIRegisterView;

    public RegisterPresenter(IRegisterView view)
    {
        mIRegisterView = view;
        mIRegisterModel = new RegisterModel();
    }

    @Override
    public void userRegister(String nickname, String email, String username, String password)
    {
        mIRegisterModel.userRegister(nickname, email, username, password, this);
        mIRegisterView.showProgress();
    }

    @Override
    public void cancelRegister()
    {
        mIRegisterView.hideProgress();
        mIRegisterModel.cancelRegister();
    }

    @Override
    public void onComplete(Object result)
    {
        mIRegisterView.hideProgress();
        mIRegisterView.registerSuccess();
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mIRegisterView.hideProgress();
        mIRegisterView.showMessage(msg.getMsg());
    }
}
