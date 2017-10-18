package space.levan.memory.model.http;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.douban.BookResultBean;
import space.levan.memory.model.bean.splash.SplashBean;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface HttpHelper {

    Flowable<SplashBean> getSplashData(int scrWidth, int scrHeight);

    Flowable<BookResultBean> getBookData(String q, int start, int count);
}
