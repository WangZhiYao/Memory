package space.levan.memory.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SignInContract;
import space.levan.memory.presenter.SignInPresenter;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/11/22
 */

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
