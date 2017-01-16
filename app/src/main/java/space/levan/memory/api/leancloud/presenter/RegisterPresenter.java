package space.levan.memory.api.leancloud.presenter;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.leancloud.model.impl.IRegisterModel;
import space.levan.memory.api.leancloud.model.RegisterModel;
import space.levan.memory.api.leancloud.presenter.impl.IRegisterPresenter;
import space.levan.memory.api.leancloud.view.IRegisterView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.utils.NetworkUtils;

/**
 * Created by WangZhiYao on 2016/12/30.
 */

public class RegisterPresenter implements IRegisterPresenter, ApiCompleteListener {

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
        mIRegisterModel.cancelRegister();
    }

    @Override
    public void onComplected(Object result)
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
