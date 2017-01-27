package space.levan.memory.api.presenter;

import android.text.TextUtils;

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

    private static final int SHAKE_NICKNAME = 1;
    private static final int SHAKE_USERNAME = 2;
    private static final int SHAKE_PASSWORD = 3;


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
        if (TextUtils.isEmpty(nickname))
        {
            mIRegisterView.shake(SHAKE_NICKNAME);
            mIRegisterView.showMessage(App.getApplication().getString(R.string.register_empty_username));
            return;
        }
        if (TextUtils.isEmpty(username))
        {
            mIRegisterView.shake(SHAKE_USERNAME);
            mIRegisterView.showMessage(App.getApplication().getString(R.string.register_empty_email));
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            mIRegisterView.shake(SHAKE_PASSWORD);
            mIRegisterView.showMessage(App.getApplication().getString(R.string.register_empty_password));
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
        mIRegisterView.showMessage(msg.getError());
    }
}
