package space.levan.memory.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.api.view.IBookListView;

public class SearchActivity extends BaseActivity implements IBookListView {

    @BindView(R.id.et_search)
    EditText mEtSearch;

    @OnClick({R.id.iv_back, R.id.iv_scan})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.iv_scan:
                break;
        }
    }

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        super.onCreate(savedInstanceState);
        initToolbar();
        ButterKnife.bind(this);

    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getLayoutInflater().inflate(R.layout.toolbar_search, mToolbar);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void refreshData(Object result) {

    }

    @Override
    public void addData(Object result) {

    }
}
