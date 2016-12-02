package space.levan.memory.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.api.presenter.impl.BookListPresenterImpl;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.bean.http.douban.BookInfoResponse;
import space.levan.memory.bean.http.douban.BookListResponse;
import space.levan.memory.view.adapter.BookListAdapter;

/**
 * Created by WangZhiYao on 2016-12-02.
 */

public class SearchResultActivity extends BaseActivity
        implements IBookListView, SwipeRefreshLayout.OnRefreshListener {

    //private static final int PRO_LOADING_SIZE = 2;
    private static String fields = "title,subtitle,origin_title,author,translator,publisher,pubdate,summary,pages,price,isbn13,images";
    private static int count = 20;
    private static int page = 0;
    private static String q;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private boolean isLoadAll;

    private GridLayoutManager mLayoutManager;
    private BookListAdapter mListAdapter;
    private List<BookInfoResponse> bookInfoResponses;
    private BookListPresenterImpl bookListPresenter;
    private int spanCount = 1;


    protected void onCreate(Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            isLoadAll = savedInstanceState.getBoolean("isLoadAll");
        }
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvents()
    {
        q = getIntent().getStringExtra("q");
        setTitle(q);
        spanCount = (int) getResources().getInteger(R.integer.home_span_count);
        bookListPresenter = new BookListPresenterImpl(this);
        bookInfoResponses = new ArrayList<>();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.recycler_color1, R.color.recycler_color2,
                R.color.recycler_color3, R.color.recycler_color4);

        mLayoutManager = new GridLayoutManager(SearchResultActivity.this, spanCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                return mListAdapter.getItemColumnSpan(position);
            }
        });
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //设置adapter
        mListAdapter = new BookListAdapter(this, bookInfoResponses, spanCount);
        mRecyclerView.setAdapter(mListAdapter);

        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mListAdapter.getItemCount())
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
    public void onRefresh()
    {
        page = 1;
        bookListPresenter.loadBooks(q, 0, count, fields);
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
    public void refreshData(Object result)
    {
        bookInfoResponses.clear();
        bookInfoResponses.addAll(((BookListResponse) result).getBooks());
        mListAdapter.notifyDataSetChanged();
        if (((BookListResponse) result).getTotal() > page * count) {
            isLoadAll = false;
        } else {
            isLoadAll = true;
        }
        page++;
    }

    @Override
    public void addData(Object result)
    {
        bookInfoResponses.addAll(((BookListResponse) result).getBooks());
        mListAdapter.notifyDataSetChanged();
        if (((BookListResponse) result).getTotal() > page * count) {
            page++;
            isLoadAll = false;
        } else {
            isLoadAll = true;
        }
    }

    private void onLoadMore()
    {
        if (!isLoadAll) {
            if (!mSwipeRefreshLayout.isRefreshing()) {
                bookListPresenter.loadBooks(q, page * count, count, fields);
            }
        } else {
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
        bookListPresenter.cancelLoading();
        super.onDestroy();
    }
}
