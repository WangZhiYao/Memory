package space.levan.memory.ui.activities;

import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.MainContract;
import space.levan.memory.presenter.MainPresenter;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    public void showMessage(String msg) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {
        mPresenter.getSplashData();
    }

    @Override
    protected void initData() {

    }
}
