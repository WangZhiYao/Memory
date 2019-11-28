package space.levan.memory.biz.widgets;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import space.levan.memory.R;
import space.levan.memory.utils.KeyboardUtils;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class SearchToolbar extends Toolbar {

    @BindView(R.id.et_toolbar_search_keywords)
    EditText mEtSearchKeywords;
    @BindView(R.id.iv_toolbar_clean_keywords)
    ImageView mIvCleanKeywords;

    private Context mContext;

    @Nullable
    private OnMenuClickListener mOnMenuClickListener;

    public SearchToolbar(Context context) {
        super(context);
        init(context);
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        View toolbarView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_toolbar_search, this);
        ButterKnife.bind(this, toolbarView);

        setContentInsetsAbsolute(0, 0);
        setContentInsetsRelative(0, 0);
    }

    public void setOnMenuClickListener(@Nullable OnMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
    }

    public void setSearchKeywords(@NonNull String keywords) {
        mEtSearchKeywords.setText(keywords);
    }

    @OnClick(R.id.iv_toolbar_back)
    public void onBackClicked() {
        if (mContext != null
                && mContext instanceof Activity
                && !((Activity) mContext).isFinishing()) {
            ((Activity) mContext).finish();
        }
    }

    @OnEditorAction(R.id.et_toolbar_search_keywords)
    public boolean onEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (mOnMenuClickListener != null && mEtSearchKeywords.getText() != null) {
                mOnMenuClickListener.onSearch(mEtSearchKeywords.getText().toString());
                KeyboardUtils.closeKeyboard(mContext, mEtSearchKeywords);
            }
        }

        return false;
    }

    @OnTextChanged(R.id.et_toolbar_search_keywords)
    public void onTextChanged(CharSequence s) {
        mIvCleanKeywords.setVisibility(TextUtils.isEmpty(s) ? GONE : VISIBLE);
    }

    @OnClick(R.id.iv_toolbar_scan)
    public void onScanClicked() {
        if (mOnMenuClickListener != null) {
            mOnMenuClickListener.onScanClicked();
        }
    }

    @OnClick(R.id.iv_toolbar_clean_keywords)
    public void onCleanKeywordsClicked() {
        mEtSearchKeywords.setText("");
        KeyboardUtils.openKeyboard(mContext, mEtSearchKeywords);
    }

    public interface OnMenuClickListener {

        void onSearch(@Nullable String keywords);

        void onScanClicked();
    }
}
