package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.particleview.ParticleView;
import space.levan.memory.R;

/**
 * 启动界面
 *
 * Created by WangZhiYao on 2016/10/22.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.pv_logo)
    ParticleView mPvLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mPvLogo = (ParticleView) findViewById(R.id.pv_logo);
        mPvLogo.setOnParticleAnimListener(() ->
        {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        });

        mPvLogo.startAnim();
    }
}
