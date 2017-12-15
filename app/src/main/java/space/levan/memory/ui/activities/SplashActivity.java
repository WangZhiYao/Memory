package space.levan.memory.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SplashContract;
import space.levan.memory.presenter.SplashPresenter;

/**
 * SplashActivity
 *
 * @author WangZhiYao
 * @date 2017/11/13
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.iv_splash_img)
    ImageView mSplashImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.fetchSplashData();
        mPresenter.isUserSignIn();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setSplashData(String picPath) {
        Glide.with(this)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(R.anim.shrink_to_middle)
                .into(mSplashImg);
    }

    @Override
    public void jumpToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void jumpToSignIn() {
        startActivity(new Intent(SplashActivity.this, SignInActivity.class));
        finish();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getActTransitionMode() {
        return 0;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
