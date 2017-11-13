package space.levan.memory.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
 * @date 2017/11/13
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected AppCompatActivity mContext;
    private Unbinder mUnbinder;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mContext = this;
        mUnbinder = ButterKnife.bind(this);
        onViewCreate();
        App.getInstance().addActivity(this);
    }

    protected abstract int getContentViewId();

    @SuppressWarnings("unchecked")
    protected void onViewCreate() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract void initInject();

    public void onBack(View v) {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnbinder.unbind();
    }
}
