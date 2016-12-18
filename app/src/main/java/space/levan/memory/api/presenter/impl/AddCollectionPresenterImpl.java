package space.levan.memory.api.presenter.impl;

import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.model.IAddCollectionModel;
import space.levan.memory.api.model.impl.AddCollectionModelImpl;
import space.levan.memory.api.presenter.IAddCollectionPresenter;
import space.levan.memory.api.view.IAddCollectionView;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.utils.common.NetworkUtils;

/**
 * Created by WangZhiYao on 2016-12-18.
 */

public class AddCollectionPresenterImpl implements IAddCollectionPresenter, ApiCompleteListener {

    private IAddCollectionModel mAddCollectionModel;
    private IAddCollectionView mIAddCollectionView;

    public AddCollectionPresenterImpl(IAddCollectionView view)
    {
        mIAddCollectionView = view;
        mAddCollectionModel = new AddCollectionModelImpl();
    }

    @Override
    public void addCollection(String username, String author, String title,
                          String img, String publisher, String subtitle,
                          String origin_title, String translator, String publish_date,
                          String pages, String isbn, String summary, String review)
    {
        if (!NetworkUtils.isConnected(App.getApplication()))
        {
            mIAddCollectionView.showMessage(App.getApplication().getString(R.string.poor_network));
            return;
        }
        mIAddCollectionView.showProgress();
        mAddCollectionModel.addCollection(username, author, title, img, publisher, subtitle,
                origin_title, translator, publish_date, pages, isbn, summary, review, this);
    }

    @Override
    public void cancelAdding()
    {
        mAddCollectionModel.cancelAdding();
    }

    @Override
    public void onComplected(Object result)
    {
        mIAddCollectionView.hideProgress();
        mIAddCollectionView.showMessage(result.toString());
    }

    @Override
    public void onFailed(BaseResponse msg)
    {
        mIAddCollectionView.hideProgress();
        mIAddCollectionView.showMessage(msg.getMsg());
    }
}
