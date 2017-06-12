package space.levan.memory.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.api.presenter.ResetPwdPresenter;
import space.levan.memory.api.view.IResetPwdView;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class ResetPwdActivity extends BaseActivity implements IResetPwdView
{
    @BindView(R.id.et_reset_password_email)
    EditText mEtResetPasswordEmail;
    @BindView(R.id.btn_reset_password_reset)
    Button mBtnResetPasswordReset;

    private String mEmail;
    private ProgressDialog mProDialog;
    private ResetPwdPresenter mResetPwdPresenter;

    @Override
    protected int getActTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
    }

    private void userResetPwd()
    {
        mResetPwdPresenter = new ResetPwdPresenter(this);
        mEmail = mEtResetPasswordEmail.getText().toString().trim();
        mResetPwdPresenter.userResetPwd(mEmail);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(ResetPwdActivity.this, null, getString(R.string.ac_reset_password_loading));
        mBtnResetPasswordReset.setClickable(false);

    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtnResetPasswordReset.setClickable(true);
    }

    @OnClick({R.id.tv_reset_password_cancel, R.id.btn_reset_password_reset})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_reset_password_cancel:
                ResetPwdActivity.this.finish();
                break;
            case R.id.btn_reset_password_reset:
                userResetPwd();
                break;
        }
    }
}
