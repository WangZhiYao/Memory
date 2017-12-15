package space.levan.memory.presenter;

import android.util.Log;

import javax.inject.Inject;

import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SearchContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.utils.RxUtils;
import space.levan.memory.utils.SubscriberUtils;

/**
 * SearchPresenter
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getBookData(String q, int start, int count) {
        addSubscribe(mDataManager.getBookData(q, start, count)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleDouBanResult())
                .subscribeWith(new SubscriberUtils<BookResult>(mView) {
                    @Override
                    public void onNext(BookResult bookResult) {
                        Log.w("WZY", bookResult.toString());
                    }
                }));
    }
}
