package space.levan.memory.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.utils.UIUtils;

/**
 * Created by WangZhiYao on 2017/4/27.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    public static final int LEFT_IN    = 1;
    public static final int LEFT_OUT   = 1;
    public static final int RIGHT_IN   = 2;
    public static final int RIGHT_OUT  = 2;

    public static BaseActivity activity;
    protected abstract int getActTransitionMode();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        switch (getActTransitionMode())
        {
            case LEFT_IN:
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
            case RIGHT_IN:
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            default:
                break;
        }
        super.onCreate(savedInstanceState);
        activity = this;
        ((App) UIUtils.getContext()).addActivity(this);
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
        switch (getActTransitionMode())
        {
            case LEFT_OUT:
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case RIGHT_OUT:
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
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
