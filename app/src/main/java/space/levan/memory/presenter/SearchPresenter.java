package space.levan.memory.presenter;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import space.levan.memory.base.RxPresenter;
import space.levan.memory.contract.SearchContract;
import space.levan.memory.model.DataManager;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.utils.RxUtils;

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
    public void startScan() {
        mView.startScan();
    }

    @Override
    public void getBookData(String q, int start, int count) {
        addSubscribe(mDataManager.getBookData(q, start, count)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<BookResult>() {
                    @Override
                    public void accept(BookResult bookResult) throws Exception {
                        //mView.showBookData(bookResult.getTotal(), bookResult.getBooks());
                    }
                }));
    }
}
