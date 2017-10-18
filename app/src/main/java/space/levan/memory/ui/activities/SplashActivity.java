package space.levan.memory.ui.activities;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import space.levan.memory.R;
import space.levan.memory.base.BaseActivity;
import space.levan.memory.contract.SplashContract;
import space.levan.memory.presenter.SplashPresenter;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.iv_splash_img)
    ImageView mSplashImg;

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setSplashData(String picPath) {
        Glide.with(this)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .animate(R.anim.shrink_to_middle)
                .into(mSplashImg);
    }

    @Override
    public void jumpToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEvent() {
        mPresenter.fetchSplashData();
    }

    @Override
    protected void initData() {

    }
}
