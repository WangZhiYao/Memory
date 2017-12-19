package space.levan.memory.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SearchContract;
import space.levan.memory.model.bean.douban.Books;
import space.levan.memory.presenter.SearchPresenter;
import space.levan.memory.ui.adapters.SearchAdapter;

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

    private static final int SEARCH = 0;
    private static final int REFRESH = 1;
    private static final int LOAD_MORE = 2;
    private String mKeywords;
    private boolean mIsLoadAll;
    private int mMode;
    private int mPage = 0;
    private int mCount = 10;
    private int mLastVisibleItem;
    private SearchAdapter mAdapter;
    private List<Books> mBooks;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mIsLoadAll = savedInstanceState.getBoolean("isLoadAll");
        }
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isLoadAll", mIsLoadAll);
        super.onSaveInstanceState(outState);
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

    private void initView() {
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mKeywords = mEtSearch.getText().toString().trim();
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH && !TextUtils.isEmpty(mKeywords)) {
                    mPresenter.getBookData(mKeywords, mPage * mCount, mCount);
                    mMode = SEARCH;
                }

                return true;
            }
        });

        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 0;
                mPresenter.getBookData(mKeywords, mPage * mCount, mCount);
                mMode = REFRESH;
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void startScan() {
        new IntentIntegrator(this)
                .setBeepEnabled(false)
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .initiateScan();
    }

    @Override
    public void showBookData(int total, List<Books> books) {
        mSwipeRefreshLayout.setEnabled(true);
        mIsLoadAll = total < mPage * mCount;
        mBooks = books;
        setAdapter();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItem + 1 == mAdapter.getItemCount()) {
                    if (!mIsLoadAll) {
                        mPage++;
                        mPresenter.getBookData(mKeywords, mPage * mCount, mCount);
                        mMode = LOAD_MORE;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void setAdapter() {
        mSwipeRefreshLayout.setRefreshing(false);
        switch (mMode) {
            case SEARCH:
                mAdapter = new SearchAdapter(this, mBooks);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case REFRESH:
                mAdapter.onRefreshData(mBooks);
                break;
            case LOAD_MORE:
                mAdapter.onAddData(mBooks);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() != null) {

            } else {
                showMessage("未扫描到任何条形码");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.iv_search_back, R.id.iv_search_scan})
    public void onOptionMenuClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                this.finish();
                break;
            case R.id.iv_search_scan:
                mPresenter.startScan();
                break;
            default:
                break;
        }
    }
}
