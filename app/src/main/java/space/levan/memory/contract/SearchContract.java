package space.levan.memory.contract;

import java.util.List;

import space.levan.memory.base.BasePresenter;
import space.levan.memory.base.BaseView;
import space.levan.memory.model.bean.douban.BookItemBean;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public interface SearchContract {

    interface View extends BaseView {

        void showBookData(List<BookItemBean> resultBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getBookData(String q, int start, int count);
    }
}
