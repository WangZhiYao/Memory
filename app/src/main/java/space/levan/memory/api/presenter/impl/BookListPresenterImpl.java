package space.levan.memory.api.presenter.impl;

import space.levan.memory.R;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.model.IBookListModel;
import space.levan.memory.api.model.impl.BookListModelImpl;
import space.levan.memory.api.presenter.IBookListPresenter;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.utils.common.NetworkUtils;
import space.levan.memory.utils.common.UIUtils;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public class BookListPresenterImpl implements IBookListPresenter , ApiCompleteListener {

    private IBookListView mBookListView;
    private IBookListModel mBoolListModel;

    public BookListPresenterImpl (IBookListView mBookListView)
    {
        this.mBookListView = mBookListView;
        mBoolListModel = new BookListModelImpl();
    }

    @Override
    public void getBookList(String KeyWords, int start, int count)
    {
        if (!NetworkUtils.isConnected(UIUtils.getContext()))
        {
            mBookListView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        else
        {
            mBookListView.showProgress();
            mBoolListModel.getBookList(KeyWords, start, count, this);
        }
    }

    @Override
    public void Success(Object response)
    {
        mBookListView.updateView(response);
        mBookListView.hideProgress();
    }

    @Override
    public void Failure(String msg)
    {
        mBookListView.showMessage(msg);
    }
}
