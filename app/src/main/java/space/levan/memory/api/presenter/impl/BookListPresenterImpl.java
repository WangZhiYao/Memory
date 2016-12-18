package space.levan.memory.api.presenter.impl;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.model.IBookListModel;
import space.levan.memory.api.model.impl.BookListModelImpl;
import space.levan.memory.api.presenter.IBookListPresenter;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.bean.http.douban.BookListResponse;
import space.levan.memory.utils.common.NetworkUtils;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public class BookListPresenterImpl implements IBookListPresenter, ApiCompleteListener {

    private IBookListView mBookListView;
    private IBookListModel mBookListModel;

    public BookListPresenterImpl(IBookListView view)
    {
        mBookListView = view;
        mBookListModel = new BookListModelImpl();
    }

    @Override
    public void loadBooks(String q, int start, int count, String fields)
    {
        if (!NetworkUtils.isConnected(App.getApplication()))
        {
            mBookListView.showMessage(App.getApplication().getString(R.string.poor_network));
            return;
        }
        mBookListView.showProgress();
        mBookListModel.loadBookList(q, start, count, fields, this);
    }

    @Override
    public void cancelLoading()
    {
        mBookListModel.cancelLoading();
    }

    @Override
    public void onComplected(Object result)
    {
        if (result instanceof BookListResponse)
        {
            int index = ((BookListResponse) result).getStart();
            if (index == 0)
            {
                mBookListView.refreshData(result);
            }
            else
            {
                mBookListView.addData(result);
            }

            mBookListView.hideProgress();
        }
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mBookListView.hideProgress();
        if (msg == null)
        {
            return;
        }
        mBookListView.showMessage(msg.getMsg());
    }
}
