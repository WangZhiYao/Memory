package space.levan.memory.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import space.levan.memory.R;
import space.levan.memory.view.activities.LoginActivity;
import space.levan.memory.view.activities.MainActivity;
import space.levan.memory.view.activities.SearchActivity;
import space.levan.memory.view.activities.ShelfActivity;
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
        view.findViewById(R.id.iv_main_shelf).setOnClickListener(this);
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
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.iv_main_shelf:
                startActivity(new Intent(getActivity(), ShelfActivity.class));
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
}
