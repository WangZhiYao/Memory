package space.levan.memory.view.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import space.levan.memory.R;
import space.levan.memory.utils.KeyBoardUtils;
import space.levan.memory.view.activities.SearchActivity;
import space.levan.memory.view.base.BaseFragment;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class SearchTitleFragment extends BaseFragment implements TextView.OnEditorActionListener {
    private EditText mEtKeywords;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.iv_search_back).setOnClickListener(this);
        view.findViewById(R.id.iv_search_scan).setOnClickListener(this);
        mEtKeywords = (EditText) view.findViewById(R.id.et_search_keywords);
        mEtKeywords.setOnEditorActionListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_title;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                getActivity().finish();
                break;
            case R.id.iv_search_scan:
                SearchActivity searchActivity = (SearchActivity) getActivity();
                searchActivity.customScan();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            SearchActivity searchActivity = (SearchActivity) getActivity();
            searchActivity.startSearch(textView.getText().toString());
            if (KeyBoardUtils.isKeyBordOpen(searchActivity)) {
                KeyBoardUtils.closeKeyBord(mEtKeywords, searchActivity);
            }
        }

        return false;
    }
}
