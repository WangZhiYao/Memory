package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.ResetPwdModel;
import space.levan.memory.api.model.impl.IResetPwdModel;
import space.levan.memory.api.presenter.impl.IResetPwdPresenter;
import space.levan.memory.api.view.IResetPwdView;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class ResetPwdPresenter implements IResetPwdPresenter, ApiListener
{
    private IResetPwdModel mIResetPwdModel;
    private IResetPwdView mIResetPwdView;

    public ResetPwdPresenter(IResetPwdView view)
    {
        mIResetPwdView = view;
        mIResetPwdModel = new ResetPwdModel();
    }

    @Override
    public void userResetPwd(String email)
    {
        mIResetPwdModel.userResetPwd(email, this);
        mIResetPwdView.showProgress();
    }

    @Override
    public void cancelReset()
    {
        mIResetPwdView.hideProgress();
        mIResetPwdModel.cancelReset();
    }

    @Override
    public void onSuccess(Object result)
    {
        mIResetPwdView.hideProgress();
        mIResetPwdView.showMessage(result.toString());
    }

    @Override
    public void onFailure(String msg)
    {
        mIResetPwdView.hideProgress();
        mIResetPwdView.showMessage(msg);
    }
}
