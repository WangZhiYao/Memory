package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.api.presenter.BookListPresenter;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.bean.douban.BookListResponse;
import space.levan.memory.utils.AnimationUtils;
import space.levan.memory.utils.KeyBoardUtils;
import space.levan.memory.view.adapter.SearchAdapter;

public class SearchActivity extends BaseActivity implements IBookListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLinLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static int count = 20;
    private static int page = 0;
    private static String q;

    private boolean isLoadAll;
    private GridLayoutManager mLayoutManager;
    private SearchAdapter mSearchAdapter;
    private List<BookInfoResponse> bookInfoResponses;
    private BookListPresenter bookListPresenter;
    private int spanCount = 1;

    private static String fields = "title,subtitle,origin_title,author,translator,publisher,pubdate,summary,pages,price,isbn13,images";

    @OnClick({R.id.iv_back, R.id.iv_scan})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.iv_scan:
                customScan();
                break;
        }
    }

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            isLoadAll = savedInstanceState.getBoolean("isLoadAll");
        }
        setContentView(R.layout.activity_search);
        initToolbar();
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);

    }

    private void initToolbar()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getLayoutInflater().inflate(R.layout.toolbar_search, mToolbar);
    }

    @Override
    protected void initEvents()
    {
        mEtSearch.setOnEditorActionListener((textView, i, keyEvent) ->
        {
            if (i == EditorInfo.IME_ACTION_SEARCH)
            {
                q = textView.getText().toString();
                startSearch();
                if (KeyBoardUtils.isKeyBordOpen(this))
                {
                    KeyBoardUtils.closeKeyBord(mEtSearch, this);
                }
            }

            return false;
        });
    }

    public void customScan()
    {
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null)
        {
            if (intentResult.getContents() == null)
            {
                //ScanResult is Empty
            }
            else
            {
                q = intentResult.getContents();
                startSearch();
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startSearch()
    {
        spanCount = getResources().getInteger(R.integer.home_span_count);
        bookListPresenter = new BookListPresenter(this);
        bookInfoResponses = new ArrayList<>();

        mLayoutManager = new GridLayoutManager(SearchActivity.this, spanCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                return mSearchAdapter.getItemColumnSpan(position);
            }
        });

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //设置adapter
        mSearchAdapter = new SearchAdapter(this, bookInfoResponses, spanCount);
        mRecyclerView.setAdapter(mSearchAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mSearchAdapter.getItemCount())
                {
                    onLoadMore();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress()
    {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void shake()
    {
        mLinLayout.startAnimation(AnimationUtils.shakeAnimation(2));
    }

    @Override
    public void refreshData(Object result)
    {
        bookInfoResponses.clear();
        bookInfoResponses.addAll(((BookListResponse) result).getBooks());
        mSearchAdapter.notifyDataSetChanged();
        if (((BookListResponse) result).getTotal() > page * count)
        {
            isLoadAll = false;
        }
        else
        {
            isLoadAll = true;
        }
        page++;
    }

    @Override
    public void addData(Object result)
    {
        bookInfoResponses.addAll(((BookListResponse) result).getBooks());
        mSearchAdapter.notifyDataSetChanged();
        if (((BookListResponse) result).getTotal() > page * count)
        {
            page++;
            isLoadAll = false;
        }
        else
        {
            isLoadAll = true;
        }
    }

    @Override
    public void onRefresh()
    {
        page = 1;
        bookListPresenter.loadBooks(q, 0, count, fields);
    }

    private void onLoadMore()
    {
        if (!isLoadAll)
        {
            if (!mSwipeRefreshLayout.isRefreshing())
            {
                bookListPresenter.loadBooks(q, page * count, count, fields);
            }
        }
        else
        {
            showMessage(getResources().getString(R.string.no_more));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putBoolean("isLoadAll", isLoadAll);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy()
    {
        //bookListPresenter.cancelLoading();
        super.onDestroy();
    }
}
