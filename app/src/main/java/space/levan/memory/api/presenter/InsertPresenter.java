package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.InsertModel;
import space.levan.memory.api.model.impl.IInsertModel;
import space.levan.memory.api.presenter.impl.IInsertPresenter;
import space.levan.memory.api.view.ISearchView;
import space.levan.memory.bean.douban.BookInfoResponse;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public class InsertPresenter implements IInsertPresenter, ApiListener
{
    private ISearchView mISearchView;
    private IInsertModel mIInsertModel;

    public InsertPresenter(ISearchView view)
    {
        mISearchView = view;
        mIInsertModel = new InsertModel();
    }

    @Override
    public void onSuccess(Object result)
    {
        mISearchView.hideProgress();
        mISearchView.showResult((Boolean) result);
    }

    @Override
    public void onFailure(String msg)
    {
        mISearchView.hideProgress();
        mISearchView.showMessage(msg);
    }

    @Override
    public void insertInfo(BookInfoResponse bookInfoResponse)
    {
        mISearchView.showProgress();
        mIInsertModel.insertInfo(bookInfoResponse, this);
    }

    @Override
    public void cancelInsert()
    {

    }
}
