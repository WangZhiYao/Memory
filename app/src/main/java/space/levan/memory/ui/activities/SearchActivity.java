package space.levan.memory.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.List;

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
}
