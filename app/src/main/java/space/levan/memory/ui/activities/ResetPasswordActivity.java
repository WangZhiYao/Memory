package space.levan.memory.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.ResetPasswordContract;
import space.levan.memory.presenter.ResetPasswordPresenter;

/**
 * Reset Password Activity
 *
 * @author WangZhiYao
 * @date 2017/12/15
 */

public class ResetPasswordActivity extends BaseActivity<ResetPasswordPresenter> implements ResetPasswordContract.View {

    @BindView(R.id.et_reset_password_email)
    EditText mEtEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected int getActTransitionMode() {
        return RIGHT;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.tv_reset_password_back, R.id.btn_reset_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reset_password_back:
                this.finish();
                break;
            case R.id.btn_reset_password:
                break;
            default:
                break;
        }
    }
}
