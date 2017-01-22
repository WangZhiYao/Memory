package space.levan.memory.view.activity;

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
import space.levan.memory.api.presenter.ResetPasswordPresenter;
import space.levan.memory.api.view.IResetPasswordView;

public class ResetPasswordActivity extends BaseActivity implements IResetPasswordView {

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.bt_reset)
    Button mBtReset;

    private ProgressDialog mProDialog;
    private ResetPasswordPresenter mResetPasswordPresenter;
    private String mEmail;

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvents()
    {
        mResetPasswordPresenter = new ResetPasswordPresenter(this);
    }

    @OnClick({R.id.tv_cancel, R.id.bt_reset})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_cancel:
                this.finish();
                break;
            case R.id.bt_reset:
                resetPassword();
                break;
        }
    }

    private void resetPassword()
    {
        mEmail = mEtEmail.getText().toString().trim();

        mResetPasswordPresenter.resetPassword(mEmail);
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(ResetPasswordActivity.this, null, getString(R.string.loading));
        mBtReset.setClickable(false);
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtReset.setClickable(true);
    }
}
