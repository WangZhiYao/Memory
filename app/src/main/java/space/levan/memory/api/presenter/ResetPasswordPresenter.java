package space.levan.memory.api.presenter;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.ResetPasswordModel;
import space.levan.memory.api.model.impl.IResetPasswordModel;
import space.levan.memory.api.presenter.impl.IResetPasswordPresenter;
import space.levan.memory.api.view.IResetPasswordView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.utils.NetworkUtils;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class ResetPasswordPresenter implements IResetPasswordPresenter, ApiListener {

    private IResetPasswordModel mIResetPasswordModel;
    private IResetPasswordView mIResetPasswordView;

    public ResetPasswordPresenter(IResetPasswordView view)
    {
        mIResetPasswordView = view;
        mIResetPasswordModel = new ResetPasswordModel();
    }

    @Override
    public void resetPassword(String email)
    {
        if (!NetworkUtils.isConnected(App.getApplication()))
        {
            mIResetPasswordView.showMessage(App.getApplication().getResources().getString(R.string.poor_network));
            return;
        }
        mIResetPasswordView.showProgress();
        mIResetPasswordModel.resetPassword(email, this);
    }

    @Override
    public void onComplete(Object result)
    {
        mIResetPasswordView.hideProgress();
        mIResetPasswordView.showMessage(result.toString());
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mIResetPasswordView.hideProgress();
        mIResetPasswordView.showMessage(msg.getError());
    }
}
