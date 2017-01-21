package space.levan.memory.api.presenter;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.RegisterModel;
import space.levan.memory.api.model.impl.IRegisterModel;
import space.levan.memory.api.presenter.impl.IRegisterPresenter;
import space.levan.memory.api.view.IRegisterView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.utils.NetworkUtils;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class RegisterPresenter implements IRegisterPresenter, ApiListener {

    private IRegisterModel mIRegisterModel;
    private IRegisterView mIRegisterView;

    public RegisterPresenter(IRegisterView view)
    {
        mIRegisterView = view;
        mIRegisterModel = new RegisterModel();
    }

    @Override
    public void userRegister(String nickname, String username, String password)
    {
        if (!NetworkUtils.isConnected(App.getApplication()))
        {
            mIRegisterView.showMessage(App.getApplication().getResources().getString(R.string.poor_network));
            return;
        }
        mIRegisterView.showProgress();
        mIRegisterModel.userRegister(nickname, username, password, this);
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
        mIRegisterView.registerSuccess(result.toString());
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mIRegisterView.hideProgress();
        mIRegisterView.registerFailed(msg.getError());
    }
}
