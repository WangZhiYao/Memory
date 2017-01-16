package space.levan.memory.view.activity;

import android.app.AlertDialog;
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
import space.levan.memory.api.leancloud.presenter.LoginPresenter;
import space.levan.memory.api.leancloud.presenter.ResetPasswordPresenter;
import space.levan.memory.api.leancloud.view.ILoginView;
import space.levan.memory.api.leancloud.view.IResetPasswordView;
import space.levan.memory.utils.KeyBoardUtils;

/**
 * Created by WangZhiYao on 2016/12/29.
 */

public class LoginActivity extends BaseActivity implements ILoginView , IResetPasswordView {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;


    private LoginPresenter loginPresenter;
    private ResetPasswordPresenter resetPasswordPresenter;
    private ProgressDialog mProDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvents()
    {

    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.tv_forget_password})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.btn_register:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 0);
                break;
            case R.id.tv_forget_password:
                resetPassword();
                break;
        }
    }

    private void userLogin()
    {
        if (KeyBoardUtils.isKeyBordOpen(this))
        {
            KeyBoardUtils.closeKeyBord(mEtPassword, this);
        }

        loginPresenter = new LoginPresenter(this);
        loginPresenter.userLogin(mEtUsername.getText().toString(), mEtPassword.getText().toString());
    }

    private void resetPassword()
    {
        EditText mEtEmail = new EditText(LoginActivity.this);
        new AlertDialog.Builder(this).setTitle("请输入邮箱")
                .setView(mEtEmail).
                setPositiveButton("确定", (dialog, which) ->
                {
                    resetPasswordPresenter = new ResetPasswordPresenter(this);
                    resetPasswordPresenter.resetPassword(mEtEmail.getText().toString());
                })
                .setNegativeButton("取消", null).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 0 && resultCode ==1)
        {
            mEtUsername.setText(data.getStringExtra("username"));
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
    public void loginFailed(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(LoginActivity.this, null, getString(R.string.login_loading));
        mBtnRegister.setClickable(false);
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtnRegister.setClickable(true);
    }
}
