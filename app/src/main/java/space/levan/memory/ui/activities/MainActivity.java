package space.levan.memory.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.MainContract;
import space.levan.memory.model.bean.project.Project;
import space.levan.memory.presenter.MainPresenter;
import space.levan.memory.ui.adapters.MainAdapter;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/11/13
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_main_empty_project)
    TextView mTvEmptyProject;

    private MainAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        mPresenter.getSplashData();
        mPresenter.getAllProject();
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
                userSignOut();
                break;
            case R.id.iv_main_shelf:
                break;
            case R.id.iv_main_scan:
                break;
            case R.id.iv_main_search:
                break;
            case R.id.fab:
                addNewProject();
                break;
            default:
                break;
        }
    }

    private void addNewProject() {
        Project project = new Project();
        project.setName("基于文本的辅助资料提取APP");
        project.setStatus("未完成");
        project.setStart_time("2016-10-01");
        project.setMember("王致尧");
        project.setNotes("毕业设计");
        mPresenter.insertNewProject(project);
        mPresenter.getAllProject();
    }

    private void userSignOut() {
        new AlertDialog.Builder(this).setTitle("退出确认")
                .setMessage("确定要退出登录吗？")
                .setPositiveButton("退出登录", (dialogInterface, i) -> {
                    AVUser.logOut();
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    this.finish();
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public void showProject(List<Project> projects) {
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mTvEmptyProject.setVisibility(View.GONE);

        adapter = new MainAdapter(this, projects);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showEmptyView() {
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mTvEmptyProject.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        mPresenter.getAllProject();
        adapter.notifyDataSetChanged();
    }
}
