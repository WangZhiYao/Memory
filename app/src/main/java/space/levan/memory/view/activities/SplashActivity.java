package space.levan.memory.view.activities;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.particleview.ParticleView;
import space.levan.memory.R;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class SplashActivity extends BaseActivity
{
    @BindView(R.id.pv_logo)
    ParticleView mPvLogo;

    @Override
    protected int getActTransitionMode()
    {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initEvents();
    }

    protected void initEvents()
    {
        mPvLogo = (ParticleView) findViewById(R.id.pv_logo);
        mPvLogo.setOnParticleAnimListener(() ->
        {

        });
        mPvLogo.startAnim();
    }

    @Override
    public void onBackPressed()
    {
        //空实现拦截在Splash时用户按下返回键导致MainActivity内存泄漏
    }
}
