package space.levan.memory.ui.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SignUpContract;
import space.levan.memory.presenter.SignUpPresenter;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public class SignUpActivity extends BaseActivity<SignUpPresenter> implements SignUpContract.View {

    @BindView(R.id.et_sign_up_email)
    EditText mEtEmail;
    @BindView(R.id.et_sign_up_password)
    EditText mEtPassword;

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void signUpSuccess() {
        this.finish();
    }

    @Override
    public void signUpFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tv_sign_in, R.id.btn_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_in:
                this.finish();
                break;
            case R.id.btn_sign_up:
                String userEmail = mEtEmail.getText().toString();
                String password = mEtPassword.getText().toString();
                mPresenter.userSignUp(userEmail, password);
                break;
            default:
                break;
        }
    }
}
