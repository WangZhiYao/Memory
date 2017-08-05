package space.levan.memory.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.api.presenter.RegisterPresenter;
import space.levan.memory.api.view.IRegisterView;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class RegisterActivity extends BaseActivity implements IRegisterView {
    @BindView(R.id.et_register_email)
    EditText mEtEmail;
    @BindView(R.id.et_register_password)
    EditText mEtPassword;
    @BindView(R.id.tv_register_login)
    TextView mTvLogin;
    @BindView(R.id.btn_register_sign_up)
    Button mBtnSignUp;
    private String mEmail;
    private String mPassword;
    private ProgressDialog mProDialog;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected int getActTransitionMode() {
        return 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    private void userRegister() {
        mRegisterPresenter = new RegisterPresenter(this);
        mEmail = mEtEmail.getText().toString().trim();
        mPassword = mEtPassword.getText().toString();
        mRegisterPresenter.userRegister(mEmail, mPassword);
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, getString(R.string.ac_register_tips), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("username", mEtEmail.getText().toString());
        intent.putExtra("password", mEtPassword.getText().toString());
        setResult(1, intent);
        this.finish();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProDialog = ProgressDialog.show(RegisterActivity.this, null, getString(R.string.ac_register_loading));
        mBtnSignUp.setClickable(false);
    }

    @Override
    public void hideProgress() {
        mProDialog.dismiss();
        mBtnSignUp.setClickable(true);
    }

    @OnClick({R.id.tv_register_login, R.id.btn_register_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_login:
                RegisterActivity.this.finish();
                break;
            case R.id.btn_register_sign_up:
                userRegister();
                break;
        }
    }
}
