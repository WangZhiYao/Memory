package space.levan.memory.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import space.levan.memory.App;
import space.levan.memory.utils.UIUtils;

/**
 * Created by WangZhiYao on 2017/4/27.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    public static BaseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = this;
        ((App) UIUtils.getContext()).addActivity(this);
        init();
    }

    private void init()
    {
        //initView();
        //initData();
    }

    //protected abstract void initView();

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
