package space.levan.memory.view.fragments;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import space.levan.memory.R;
import space.levan.memory.utils.JxlUtils;
import space.levan.memory.view.base.BaseFragment;

/**
 * Created by WangZhiYao on 2017/5/3.
 */

public class AddCollectionTitleFragment extends BaseFragment {
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.iv_add_collection_back).setOnClickListener(this);
        view.findViewById(R.id.iv_add_collection_excel).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_collection_title;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_collection_back:
                getActivity().finish();
                break;
            case R.id.iv_add_collection_excel:
                showExportDialog();
                break;
        }
    }

    private void showExportDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.fg_add_collection_title_dialog_title)
                .setMessage(R.string.fg_add_collection_title_dialog_message)
                .setPositiveButton(R.string.fg_add_collection_title_dialog_sure,
                        (dialogInterface, i) -> JxlUtils.exportExcel(getActivity()))
                .setNegativeButton(R.string.fg_add_collection_title_dialog_cancel, null)
                .show();
    }
}
