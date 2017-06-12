package space.levan.memory.api.presenter;

import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.DeleteModel;
import space.levan.memory.api.model.impl.IDeleteModel;
import space.levan.memory.api.presenter.impl.IDeletePresenter;
import space.levan.memory.api.view.ISearchView;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public class DeletePresenter implements IDeletePresenter, ApiListener
{
    private ISearchView mISearchView;
    private IDeleteModel mIDeleteModel;

    public DeletePresenter(ISearchView view)
    {
        mISearchView = view;
        mIDeleteModel = new DeleteModel();
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
    public void deleteInfo(String isbn)
    {
        mISearchView.showProgress();
        mIDeleteModel.deleteInfo(isbn, this);
    }

    @Override
    public void cancelDelete()
    {

    }
}
