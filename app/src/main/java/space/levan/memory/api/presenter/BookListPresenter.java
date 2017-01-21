package space.levan.memory.api.presenter;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.BookListModel;
import space.levan.memory.api.model.impl.IBookListModel;
import space.levan.memory.api.presenter.impl.IBookListPresenter;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.bean.douban.BookListResponse;
import space.levan.memory.utils.NetworkUtils;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class BookListPresenter implements IBookListPresenter, ApiListener {

    private IBookListView mBookListView;
    private IBookListModel mBookListModel;

    public BookListPresenter(IBookListView view)
    {
        mBookListView = view;
        mBookListModel = new BookListModel();
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
        mBookListView.hideProgress();
        mBookListModel.cancelLoading();
    }

    @Override
    public void onComplete(Object result)
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
