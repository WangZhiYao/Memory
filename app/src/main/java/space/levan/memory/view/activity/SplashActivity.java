package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.avos.avoscloud.AVUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.particleview.ParticleView;
import space.levan.memory.R;

/**
 * Created by WangZhiYao on 2017-01-22.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.pv_logo)
    ParticleView mPvLogo;

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvents()
    {
        mPvLogo = (ParticleView) findViewById(R.id.pv_logo);
        mPvLogo.setOnParticleAnimListener(() ->
        {

            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null && currentUser.getBoolean("emailVerified"))
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                this.finish();
            }
            else
            {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                this.finish();
            }
        });

        mPvLogo.startAnim();
    }

    @Override
    public void onBackPressed()
    {
        //空实现拦截在Splash时用户按下返回键导致MainActivity内存泄漏
    }
}
