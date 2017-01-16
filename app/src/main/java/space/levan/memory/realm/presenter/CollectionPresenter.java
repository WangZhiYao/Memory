package space.levan.memory.realm.presenter;

import io.realm.RealmObject;
import io.realm.RealmResults;
import space.levan.memory.bean.Book;
import space.levan.memory.realm.CompleteListener;
import space.levan.memory.realm.model.impl.ICollectionModel;
import space.levan.memory.realm.model.CollectionModel;
import space.levan.memory.realm.presenter.impl.ICollectionPresenter;
import space.levan.memory.realm.view.ICollectionView;

/**
 * Created by WangZhiYao on 2017/1/9.
 */

public class CollectionPresenter implements ICollectionPresenter, CompleteListener {

    private ICollectionView mICollectionView;
    private ICollectionModel mCollectionModel;

    public CollectionPresenter(ICollectionView view)
    {
        mICollectionView = view;
        mCollectionModel = new CollectionModel();
    }

    @Override
    public void checkCollection(String isbn)
    {
        mCollectionModel.checkCollection(isbn, this);
    }

    @Override
    public void addCollection(RealmObject book)
    {
        mCollectionModel.addCollection(book, this);
    }

    @Override
    public void cancelCollection(String isbn)
    {
        mCollectionModel.cancelCollection(isbn, this);
    }

    @Override
    public void onAddSuccess()
    {
        mICollectionView.onAddSuccess();
    }

    @Override
    public void onCancelSuccess()
    {
        mICollectionView.onCancelSuccess();
    }

    @Override
    public void isCollection(boolean isCollection)
    {
        if (isCollection)
        {
            mICollectionView.initFab(true);
        }
        else
        {
            mICollectionView.initFab(false);
        }
    }
}
