package space.levan.memory.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.MainContract;
import space.levan.memory.presenter.MainPresenter;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/11/13
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getSplashData();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.iv_main_logout, R.id.iv_main_shelf, R.id.iv_main_scan, R.id.iv_main_search, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_logout:
                break;
            case R.id.iv_main_shelf:
                break;
            case R.id.iv_main_scan:
                break;
            case R.id.iv_main_search:
                break;
            case R.id.fab:
                break;
            default:
                break;
        }
    }
}
