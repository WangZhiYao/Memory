package space.levan.memory.view.fragments;

import android.os.Bundle;
import android.view.View;

import space.levan.memory.R;
import space.levan.memory.view.base.BaseFragment;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class ShelfTitleFragment extends BaseFragment
{
    @Override
    protected void initView(View view, Bundle savedInstanceState)
    {
        view.findViewById(R.id.iv_shelf_back).setOnClickListener(this);
        view.findViewById(R.id.iv_shelf_add).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_shelf_title;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_shelf_back:
                getActivity().finish();
                break;
            case R.id.iv_shelf_add:
                //TODO 添加收藏
                break;
        }
    }
}
