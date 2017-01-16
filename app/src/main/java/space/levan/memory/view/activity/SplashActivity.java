package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.avos.avoscloud.AVUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.particleview.ParticleView;
import space.levan.memory.R;

/**
 * 启动界面
 *
 * Created by WangZhiYao on 2016/10/22.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.pv_logo)
    ParticleView mPvLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
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
}
