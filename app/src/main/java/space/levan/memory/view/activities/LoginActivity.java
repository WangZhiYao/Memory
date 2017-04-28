package space.levan.memory.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView
{
    @BindView(R.id.et_login_email)
    EditText mEtEmail;
    @BindView(R.id.et_login_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login_login)
    Button mBtnLogin;

    private String mUsername;
    private String mPassword;
    private ProgressDialog mProDialog;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 0 && resultCode == 1)
        {
            mEtEmail.setText(data.getStringExtra("username"));
            mEtPassword.setText(data.getStringExtra("password"));
        }
    }

    private void userLogin()
    {
        mLoginPresenter = new LoginPresenter(this);
        mUsername = mEtEmail.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
        mLoginPresenter.userLogin(mUsername, mPassword);
    }

    @Override
    public void loginSuccess()
    {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        this.finish();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(LoginActivity.this, null, getString(R.string.ac_login_loading));
        mBtnLogin.setClickable(false);
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtnLogin.setClickable(true);
    }

    @OnClick({R.id.tv_login_register, R.id.tv_login_reset_password, R.id.btn_login_login})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_login_register:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 0);
                break;
            case R.id.tv_login_reset_password:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.btn_login_login:
                userLogin();
                break;
        }
    }
}
