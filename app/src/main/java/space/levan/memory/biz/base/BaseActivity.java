package space.levan.memory.biz.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import space.levan.memory.R;
import space.levan.memory.annotation.TransitionMode;
import space.levan.memory.biz.widgets.LoadingDialog;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    /**
     * 获取Activity 切换方式
     *
     * @return {@link TransitionMode#TRANSITION_MODE_DEFAULT 没有任何切换动画,
     * @link TransitionMode#TRANSITION_MODE_LEFT 左进左出,
     * @link TransitionMode#TRANSITION_MODE_RIGHT 右进右出}
     */
    @TransitionMode
    protected abstract int getTransitionMode();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        switch (getTransitionMode()) {
            case TransitionMode.TRANSITION_MODE_DEFAULT:
            default:
                break;
            case TransitionMode.TRANSITION_MODE_LEFT:
                this.overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
                break;
            case TransitionMode.TRANSITION_MODE_RIGHT:
                this.overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        switch (getTransitionMode()) {
            case TransitionMode.TRANSITION_MODE_DEFAULT:
            default:
                break;
            case TransitionMode.TRANSITION_MODE_LEFT:
                this.overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
                break;
            case TransitionMode.TRANSITION_MODE_RIGHT:
                this.overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
        }
    }

    /**
     * 展示LoadingDialog
     *
     * @param context
     * @param title   loading dialog 标题
     */
    public void showLoading(Context context, @NonNull String title) {
        showLoading(context, title, null);
    }

    public void showLoading(Context context, @NonNull String title, @Nullable String message) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(context);
        }
        mLoadingDialog.setTitle(title);
        mLoadingDialog.setMessage(message);
        mLoadingDialog.show();
    }

    /**
     * 隐藏LoadingDialog
     */
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
    }
}
