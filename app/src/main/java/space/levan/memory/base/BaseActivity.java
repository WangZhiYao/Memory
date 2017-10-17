package space.levan.memory.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import space.levan.memory.app.App;
import space.levan.memory.di.component.ActivityComponent;
import space.levan.memory.di.component.DaggerActivityComponent;
import space.levan.memory.di.module.ActivityModule;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected Activity mContext;
    private Unbinder mUnBinder;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        onViewCreate();
        App.getInstance().addActivity(this);
        initEvent();
        initData();
    }

    protected void onViewCreate() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEvent();

    protected abstract void initData();
}
