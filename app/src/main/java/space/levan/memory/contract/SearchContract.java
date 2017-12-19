package space.levan.memory.contract;

import java.util.List;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;
import space.levan.memory.model.bean.douban.Books;

/**
 * Contract for SearchPresenter
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public interface SearchContract {

    interface View extends BaseView {

        /**
         * callback
         */
        void startScan();

        void showBookData(int total, List<Books> books);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * get book info from DouBan api
         *
         * @param q     keywords
         * @param start start with this number of all the result
         * @param count how many result will be returned from the server
         */
        void getBookData(String q, int start, int count);

        /**
         * start barcode scanner
         */
        void startScan();
    }
}
