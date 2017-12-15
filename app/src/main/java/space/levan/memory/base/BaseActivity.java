package space.levan.memory.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import space.levan.memory.R;
import space.levan.memory.app.App;
import space.levan.memory.di.component.ActivityComponent;
import space.levan.memory.di.component.DaggerActivityComponent;
import space.levan.memory.di.module.ActivityModule;

/**
 * BaseActivity
 *
 * @author WangZhiYao
 * @date 2017/11/13
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T mPresenter;

    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    protected AppCompatActivity mContext;
    private Unbinder mUnBinder;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        switch (getActTransitionMode()) {
            case LEFT:
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            default:
                overridePendingTransition(R.anim.center_in, R.anim.left_out);
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        onViewCreate();
        App.getInstance().addActivity(this);
    }

    /**
     * get layout id
     *
     * @return layout id
     */
    protected abstract int getContentViewId();

    /**
     * set Activity Transition Animation
     *
     * @return
     */
    protected abstract int getActTransitionMode();

    @SuppressWarnings("unchecked")
    protected void onViewCreate() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * binding with injection
     */
    protected abstract void initInject();

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
    public void finish() {
        super.finish();
        switch (getActTransitionMode()) {
            case LEFT:
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
            default:
                break;
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
}
