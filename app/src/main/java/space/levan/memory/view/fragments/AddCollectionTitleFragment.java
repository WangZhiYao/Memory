package space.levan.memory.view.fragments;

import android.os.Bundle;
import android.view.View;

import space.levan.memory.R;
import space.levan.memory.view.base.BaseFragment;

/**
 * Created by WangZhiYao on 2017/5/3.
 */

public class AddCollectionTitleFragment extends BaseFragment
{
    @Override
    protected void initView(View view, Bundle savedInstanceState)
    {
        view.findViewById(R.id.iv_add_collection_back).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_add_collection_title;
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.iv_add_collection_back)
        {
            getActivity().finish();
        }
    }
}
