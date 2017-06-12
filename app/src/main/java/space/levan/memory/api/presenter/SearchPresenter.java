package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.SearchModel;
import space.levan.memory.api.model.impl.ISearchModel;
import space.levan.memory.api.presenter.impl.ISearchPresenter;
import space.levan.memory.api.view.ISearchView;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public class SearchPresenter implements ISearchPresenter, ApiListener
{
    private ISearchView mISearchView;
    private ISearchModel mISearchModel;

    public SearchPresenter(ISearchView view)
    {
        mISearchView = view;
        mISearchModel = new SearchModel();
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
    public void searchByISBN(String isbn)
    {
        mISearchView.showProgress();
        mISearchModel.searchByISBN(isbn, this);
    }

    @Override
    public void cancelSearch()
    {

    }
}
