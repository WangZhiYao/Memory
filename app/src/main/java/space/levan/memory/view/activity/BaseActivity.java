package space.levan.memory.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.utils.UIUtils;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final int LEFT_IN    = 1;
    public static final int LEFT_OUT   = 1;
    public static final int RIGHT_IN   = 2;
    public static final int RIGHT_OUT  = 2;
    protected abstract int getOverridePendingTransitionMode();
    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    //protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        switch (getOverridePendingTransitionMode())
        {
            case LEFT_IN:
                overridePendingTransition(R.anim.left_in, R.anim.stay);
                break;

            case RIGHT_IN:
                overridePendingTransition(R.anim.right_in, R.anim.stay);
                break;

            default:
                break;
        }
        super.onCreate(savedInstanceState);
        activity = this;
        ((App) UIUtils.getContext()).addActivity(this);
        init();
    }

    private void init()
    {
        initData();
        initEvents();
    }

    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected abstract void initEvents();

    /**
     * 绑定数据
     */
    protected void initData() {}

    /**
     * 获取toolbar
     *
     * @return
     */
    /*public Toolbar getToolbar()
    {
        return mToolbar;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar)
        {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    */

    /**
     * 菜单按钮初始化
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(getMenuID(), menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * 默认toolbar不带menu，复写该方法指定menu
     *
     * @return
     */
    protected int getMenuID()
    {
        return R.menu.menu_empty;
    }

    /**
     * 是否初始化状态栏
     *
     * @return
     */
    protected boolean isInitSystemBar()
    {
        return true;
    }

    /**
     * 是否显示菜单  默认显示
     *
     * @return
     */
    protected boolean showMenu()
    {
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        activity = null;
    }

    @Override
    public void finish()
    {
        super.finish();
        ((App) UIUtils.getContext()).removeActivity(this);
        switch (getOverridePendingTransitionMode())
        {
            case LEFT_OUT:
                overridePendingTransition(0, R.anim.left_out);
                break;

            case RIGHT_OUT:
                overridePendingTransition(0, R.anim.right_out);
                break;

            default:
                break;
        }
    }
    /**
     * activity退出时将activity移出栈
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ((App) UIUtils.getContext()).removeActivity(this);
    }
}
