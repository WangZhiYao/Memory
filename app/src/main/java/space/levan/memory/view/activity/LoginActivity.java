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
import space.levan.memory.api.presenter.LoginPresenter;
import space.levan.memory.api.view.ILoginView;
import space.levan.memory.utils.AnimationUtils;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_login)
    Button mBtLogin;

    private ProgressDialog mProDialog;
    private LoginPresenter mLoginPresenter;
    private String mEmail;
    private String mPassword;
    private static final int SHAKE_USERNAME = 1;
    private static final int SHAKE_PASSWORD = 2;

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvents()
    {
        mLoginPresenter = new LoginPresenter(this);
    }

    @OnClick({R.id.tv_register, R.id.tv_reset_password, R.id.bt_login})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_register:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 0);
                break;
            case R.id.tv_reset_password:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.bt_login:
                userLogin();
                break;
        }
    }

    private void userLogin()
    {
        mEmail = mEtEmail.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();

        mLoginPresenter.userLogin(mEmail, mPassword);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 0 && resultCode ==1)
        {
            mEtEmail.setText(data.getStringExtra("username"));
            mEtPassword.setText(data.getStringExtra("password"));
        }
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        this.finish();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(LoginActivity.this, null, getString(R.string.loading));
        mBtLogin.setClickable(false);
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtLogin.setClickable(true);
    }

    @Override
    public void shake(int code)
    {
        switch (code)
        {
            case SHAKE_USERNAME:
                mEtEmail.startAnimation(AnimationUtils.shakeAnimation(2));
                break;

            case SHAKE_PASSWORD:
                mEtPassword.startAnimation(AnimationUtils.shakeAnimation(2));
                break;
        }
    }
}
