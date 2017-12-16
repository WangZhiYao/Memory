package space.levan.memory.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SearchContract;
import space.levan.memory.model.bean.douban.Books;
import space.levan.memory.presenter.SearchPresenter;

/**
 * SearchActivity
 *
 * @author WangZhiYao
 * @date 2017/11/23
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.getBookData("计算机", 0, 10);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected int getActTransitionMode() {
        return RIGHT;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBookData(List<Books> resultBean) {

    }

    @OnClick({R.id.iv_search_back, R.id.iv_search_scan})
    public void onOptionMenuClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                break;
            case R.id.iv_search_scan:
                break;
            default:
                break;
        }
    }
}
