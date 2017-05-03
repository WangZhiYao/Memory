package space.levan.memory.view.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import space.levan.memory.R;
import space.levan.memory.view.adapter.ShelfAdapter;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class ShelfActivity extends BaseActivity
{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;
    private RealmChangeListener realmListener;

    @Override
    protected int getActTransitionMode()
    {
        return 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        ButterKnife.bind(this);
        initEvents();
        mSwipeRefreshLayout.setEnabled(false);
        realm = Realm.getDefaultInstance();
        realmListener = (RealmChangeListener<Realm>) realm1 -> initEvents();
        realm.addChangeListener(realmListener);
    }

    protected void initEvents()
    {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ShelfAdapter mAdapter = new ShelfAdapter(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }
}
