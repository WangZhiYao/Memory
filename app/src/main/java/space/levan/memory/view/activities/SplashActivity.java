package space.levan.memory.view.activities;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.SyncUser;
import me.wangyuwei.particleview.ParticleView;
import space.levan.memory.R;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.pv_logo)
    ParticleView mPvLogo;

    @Override
    protected int getActTransitionMode() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initEvents();
    }

    protected void initEvents() {
        mPvLogo = (ParticleView) findViewById(R.id.pv_logo);
        mPvLogo.setOnParticleAnimListener(() ->
        {
            if (SyncUser.currentUser() != null) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                this.finish();
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                this.finish();
            }
        });
        mPvLogo.startAnim();
    }

    @Override
    public void onBackPressed() {
        //空实现拦截在Splash时用户按下返回键导致MainActivity内存泄漏
    }
}
