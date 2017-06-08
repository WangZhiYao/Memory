package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.BookListModel;
import space.levan.memory.api.model.impl.IBookListModel;
import space.levan.memory.api.presenter.impl.IBookListPresenter;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.bean.douban.BookListResponse;

/**
 * Created by WangZhiYao on 2017/4/28.
 */

public class BookListPresenter implements IBookListPresenter, ApiListener
{
    private IBookListView mBookDetailView;
    private IBookListModel mBookDetailModel;

    public BookListPresenter(IBookListView view)
    {
        mBookDetailView = view;
        mBookDetailModel = new BookListModel();
    }

    @Override
    public void loadBooks(String q, int start, int count, String fields)
    {
        mBookDetailView.showProgress();
        mBookDetailModel.loadBookList(q, start, count, fields, this);
    }

    @Override
    public void cancelLoading()
    {
        mBookDetailView.hideProgress();
        mBookDetailModel.cancelLoading();
    }

    @Override
    public void onSuccess(Object result)
    {
        if (result instanceof BookListResponse)
        {
            int index = ((BookListResponse) result).getStart();
            if (index == 0)
            {
                mBookDetailView.refreshData(result);
            }
            else
            {
                mBookDetailView.addData(result);
            }

            mBookDetailView.hideProgress();
        }
    }

    @Override
    public void onFailure(String msg)
    {
        mBookDetailView.hideProgress();
        if (msg == null)
        {
            return;
        }

        mBookDetailView.showMessage(msg);
    }
}
