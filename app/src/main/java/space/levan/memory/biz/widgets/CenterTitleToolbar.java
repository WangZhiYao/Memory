package space.levan.memory.biz.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class CenterTitleToolbar extends Toolbar {

    @BindView(R.id.iv_toolbar_back)
    ImageView mIvBack;
    @BindView(R.id.tv_toolbar_title)
    TextView mTvTitle;
    @BindView(R.id.iv_toolbar_menu)
    ImageView mIvMenu;

    private Context mContext;
    private OnMenuClickListener mOnMenuClickListener;

    public CenterTitleToolbar(Context context) {
        super(context);
        init(context, null);
    }

    public CenterTitleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CenterTitleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        mContext = context;

        View toolbarView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_toolbar_center_title, this);
        ButterKnife.bind(this, toolbarView);

        if (attrs != null) {
            TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CenterTitleToolbar);
            String title = ta.getString(R.styleable.CenterTitleToolbar_title);
            boolean showBack = ta.getBoolean(R.styleable.CenterTitleToolbar_showBack, false);
            int menuResId = ta.getResourceId(R.styleable.CenterTitleToolbar_rightMenu, -1);
            ta.recycle();

            if (!TextUtils.isEmpty(title)) {
                mTvTitle.setText(title);
            }

            mIvBack.setVisibility(showBack ? VISIBLE : GONE);
            if (menuResId != -1) {
                mIvMenu.setVisibility(VISIBLE);
                mIvMenu.setImageResource(menuResId);
            } else {
                mIvMenu.setVisibility(GONE);
            }
        }

        setContentInsetsAbsolute(0, 0);
        setContentInsetsRelative(0, 0);
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
    }

    @OnClick(R.id.iv_toolbar_back)
    public final void onBackClicked() {
        if (mContext != null
                && mContext instanceof Activity
                && !((Activity) mContext).isFinishing()) {
            ((Activity) mContext).finish();
        }
    }

    @OnClick(R.id.iv_toolbar_menu)
    public final void onMenuClicked() {
        if (mOnMenuClickListener != null) {
            mOnMenuClickListener.onMenuClicked();
        }
    }

    public interface OnMenuClickListener {
        void onMenuClicked();
    }
}
