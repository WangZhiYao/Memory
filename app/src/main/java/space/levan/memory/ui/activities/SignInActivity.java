package space.levan.memory.ui.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SignInContract;
import space.levan.memory.presenter.SignInPresenter;

/**
 * SignInActivity
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInContract.View {

    @BindView(R.id.et_sign_in_email)
    EditText mEtEmail;
    @BindView(R.id.et_sign_in_password)
    EditText mEtPassword;

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected int getActTransitionMode() {
        return RIGHT;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.tv_sign_in_register, R.id.tv_sign_in_reset_password, R.id.btn_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_in_register:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
            case R.id.tv_sign_in_reset_password:
                startActivity(new Intent(SignInActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.btn_sign_in:
                String userEmail = mEtEmail.getText().toString();
                String password = mEtPassword.getText().toString();
                mPresenter.userSignIn(userEmail, password);
                break;
            default:
                break;
        }
    }

    @Override
    public void setUserEmail(String userEmail) {
        mEtEmail.setText(userEmail);
    }

    @Override
    public void signInSuccess() {
        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        this.finish();
    }

    @Override
    public void signInFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.fetchUserEmail();
    }
}
