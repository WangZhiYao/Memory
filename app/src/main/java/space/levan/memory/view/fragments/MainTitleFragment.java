package space.levan.memory.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.avos.avoscloud.AVUser;

import space.levan.memory.R;
import space.levan.memory.view.activities.AddCollectionActivity;
import space.levan.memory.view.activities.LoginActivity;
import space.levan.memory.view.activities.MainActivity;
import space.levan.memory.view.activities.SearchActivity;
import space.levan.memory.view.base.BaseFragment;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class MainTitleFragment extends BaseFragment
{
    @Override
    protected void initView(View view, Bundle savedInstanceState)
    {
        view.findViewById(R.id.iv_main_logout).setOnClickListener(this);
        view.findViewById(R.id.iv_main_add).setOnClickListener(this);
        view.findViewById(R.id.iv_main_search).setOnClickListener(this);
        view.findViewById(R.id.iv_main_scan).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_main_title;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_main_logout:
                showLogoutDialog();
                break;
            case R.id.iv_main_add:
                startActivity(new Intent(getActivity(), AddCollectionActivity.class));
                break;
            case R.id.iv_main_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.iv_main_scan:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.customScan();
                break;
        }
    }

    private void showLogoutDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.fg_main_title_dialog_title));
        builder.setMessage(getString(R.string.fg_main_title_dialog_message));
        builder.setPositiveButton(getString(R.string.fg_main_title_dialog_sure), (dialog, which) ->
        {
            AVUser.logOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
        builder.setNegativeButton(getString(R.string.fg_main_title_dialog_cancel),
                (dialog, which) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
