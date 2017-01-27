package space.levan.memory.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.api.presenter.RegisterPresenter;
import space.levan.memory.api.view.IRegisterView;
import space.levan.memory.utils.AnimationUtils;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @BindView(R.id.et_nickname)
    EditText mEtNickname;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_register)
    Button mBtRegister;

    private ProgressDialog mProDialog;
    private RegisterPresenter mRegisterPresenter;

    private String mNickname;
    private String mEmail;
    private String mPassword;

    private static final int SHAKE_NICKNAME = 1;
    private static final int SHAKE_USERNAME = 2;
    private static final int SHAKE_PASSWORD = 3;

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvents()
    {
        mRegisterPresenter = new RegisterPresenter(this);
    }

    @OnClick({R.id.tv_login, R.id.bt_register})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_login:
                this.finish();
                break;
            case R.id.bt_register:
                userRegister();
                break;
        }
    }

    private void userRegister()
    {
        mNickname = mEtNickname.getText().toString().trim();
        mEmail = mEtEmail.getText().toString().trim();
        mPassword = mEtPassword.getText().toString();

        mRegisterPresenter.userRegister(mNickname, mEmail, mPassword);
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess(String msg)
    {
        Toast.makeText(this, msg + "验证邮箱后才能登录", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("username", mEtEmail.getText().toString());
        intent.putExtra("password", mEtPassword.getText().toString());
        setResult(1, intent);
        this.finish();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(RegisterActivity.this, null, getString(R.string.loading));
        mBtRegister.setClickable(false);
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtRegister.setClickable(true);
    }

    @Override
    public void shake(int code)
    {
        switch (code)
        {
            case SHAKE_NICKNAME:
                mEtNickname.startAnimation(AnimationUtils.shakeAnimation(2));
                break;

            case SHAKE_USERNAME:
                mEtEmail.startAnimation(AnimationUtils.shakeAnimation(2));
                break;

            case SHAKE_PASSWORD:
                mEtPassword.startAnimation(AnimationUtils.shakeAnimation(2));
                break;
        }
    }
}
