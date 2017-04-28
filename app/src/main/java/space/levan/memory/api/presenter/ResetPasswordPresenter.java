package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.ResetPasswordModel;
import space.levan.memory.api.model.impl.IResetPasswordModel;
import space.levan.memory.api.presenter.impl.IResetPasswordPresenter;
import space.levan.memory.api.view.IResetPasswordView;
import space.levan.memory.bean.douban.BaseResponse;

/**
 * Created by WangZhiYao on 2017/4/14.
 */

public class ResetPasswordPresenter implements IResetPasswordPresenter, ApiListener
{
    private IResetPasswordModel mIResetPwdModel;
    private IResetPasswordView mIResetPwdView;

    public ResetPasswordPresenter(IResetPasswordView view)
    {
        mIResetPwdView = view;
        mIResetPwdModel = new ResetPasswordModel();
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
    public void onComplete(Object result)
    {
        mIResetPwdView.hideProgress();
        mIResetPwdView.showMessage(result.toString());
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mIResetPwdView.hideProgress();
        mIResetPwdView.showMessage(msg.getMsg());
    }
}
