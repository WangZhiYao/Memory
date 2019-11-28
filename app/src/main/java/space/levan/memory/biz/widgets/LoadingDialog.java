package space.levan.memory.biz.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.utils.UIUtils;

/**
 * @author WangZhiYao
 * @date 2019/8/4
 */
public class LoadingDialog extends Dialog {

    private static final int MIN_SHOW_TIME = 500;
    private static final int MIN_DELAY = 200;

    @BindView(R.id.tv_loading_dialog_title)
    TextView mTvTitle;
    @BindView(R.id.tv_loading_dialog_message)
    TextView mTvMessage;

    private long mStartTime;
    private boolean mPostedShow;
    private boolean mPostedHide;
    private boolean mDismissed;

    private Handler mHandler;

    private final Runnable mDelayedShow = () -> {
        mPostedShow = false;
        if (!mDismissed) {
            mStartTime = System.currentTimeMillis();
            show();
        }
    };

    private final Runnable mDelayedHide = () -> {
        mPostedHide = false;
        mStartTime = -1L;
        dismiss();
    };

    public LoadingDialog(Context context) {
        super(context, R.style.Theme_AppCompat_Dialog);
        init(context);
    }

    private void init(Context context) {
        View loadingDialog = LayoutInflater.from(context)
                .inflate(R.layout.layout_dialog_loading, null);
        setContentView(loadingDialog);
        ButterKnife.bind(this, loadingDialog);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams params = dialogWindow.getAttributes();
            params.width = (int) (UIUtils.getScreenWidth(context) * 0.8);
            dialogWindow.setAttributes(params);
        }

        mHandler = new Handler();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    @Override
    public void setTitle(@StringRes int resId) {
        mTvTitle.setText(resId);
    }

    public void setTitle(@NonNull String title) {
        mTvTitle.setText(title);
    }

    public void setMessage(@StringRes int resId) {
        mTvMessage.setText(resId);
    }

    public void setMessage(@Nullable String message) {
        if (TextUtils.isEmpty(message)) {
            mTvMessage.setVisibility(View.GONE);
        } else {
            mTvMessage.setVisibility(View.VISIBLE);
            mTvMessage.setText(message);
        }
    }

    private void removeCallbacks() {
        mHandler.removeCallbacks(mDelayedHide);
        mHandler.removeCallbacks(mDelayedShow);
    }

    @Override
    public synchronized void show() {
        super.show();
        // Reset the start time.
        mStartTime = -1;
        mDismissed = false;
        mHandler.removeCallbacks(mDelayedHide);
        mPostedHide = false;
        if (!mPostedShow) {
            mHandler.postDelayed(mDelayedShow, MIN_DELAY);
            mPostedShow = true;
        }
    }

    @Override
    public synchronized void hide() {
        mDismissed = true;
        mHandler.removeCallbacks(mDelayedShow);
        mPostedShow = false;
        long diff = System.currentTimeMillis() - mStartTime;
        if (diff >= MIN_SHOW_TIME || mStartTime == -1) {
            // The progress spinner has been shown long enough
            // OR was not shown yet. If it wasn't shown yet,
            // it will just never be shown.
            dismiss();
        } else {
            // The progress spinner is shown, but not long enough,
            // so put a delayed message in to hide it when its been
            // shown long enough.
            if (!mPostedHide) {
                mHandler.postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
                mPostedHide = true;
            }
        }
    }

    public static class Builder {

        private String mTitle;
        private String mMessage;

        private LoadingDialog mLoadingDialog;

        public Builder(Context context) {
            mLoadingDialog = new LoadingDialog(context);
        }

        public Builder setTitle(@NonNull String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(@Nullable String message) {
            mMessage = message;
            return this;
        }

        public LoadingDialog build() {
            mLoadingDialog.setTitle(mTitle);
            mLoadingDialog.setMessage(mMessage);
            return mLoadingDialog;
        }
    }
}
