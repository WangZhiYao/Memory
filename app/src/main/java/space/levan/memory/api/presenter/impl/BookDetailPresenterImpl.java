package space.levan.memory.api.presenter.impl;

import space.levan.memory.R;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.model.IBookDetailModel;
import space.levan.memory.api.model.impl.BookDetailModelImpl;
import space.levan.memory.api.presenter.IBookDetailPresenter;
import space.levan.memory.api.view.IBookDetailView;
import space.levan.memory.utils.common.NetworkUtils;
import space.levan.memory.utils.common.UIUtils;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class BookDetailPresenterImpl implements IBookDetailPresenter, ApiCompleteListener {

    private IBookDetailView mBookDetailView;
    private IBookDetailModel mBookDetailModel;

    public BookDetailPresenterImpl(IBookDetailView mBookDetailView)
    {
        this.mBookDetailView = mBookDetailView;
        mBookDetailModel = new BookDetailModelImpl();
    }

    @Override
    public void getBookDetail(String urlString)
    {
        if (!NetworkUtils.isConnected(UIUtils.getContext()))
        {
            mBookDetailView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            //mBookDetailView.hideProgress();
        } else {
            mBookDetailView.showProgress();
            mBookDetailModel.getBookDetail(urlString, this);
        }
    }

    @Override
    public void Success(Object response)
    {
        mBookDetailView.updateView(response);
        mBookDetailView.hideProgress();
    }

    @Override
    public void Failure(String msg)
    {
        mBookDetailView.hideProgress();
        if (msg == null) return;
        mBookDetailView.showMessage(msg);
    }
}
