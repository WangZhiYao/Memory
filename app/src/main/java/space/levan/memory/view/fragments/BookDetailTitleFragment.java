package space.levan.memory.view.fragments;

import android.os.Bundle;
import android.view.View;

import space.levan.memory.R;
import space.levan.memory.view.base.BaseFragment;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class BookDetailTitleFragment extends BaseFragment
{
    @Override
    protected void initView(View view, Bundle savedInstanceState)
    {
        view.findViewById(R.id.iv_book_detail_back).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_book_detail_title;
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.iv_book_detail_back)
        {
            getActivity().finish();
        }
    }
}
