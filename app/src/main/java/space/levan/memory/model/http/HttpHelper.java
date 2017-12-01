package space.levan.memory.model.http;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.model.bean.splash.Splash;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface HttpHelper {

    Flowable<Splash> getSplashData(int scrWidth, int scrHeight);

    Flowable<BookResult> getBookData(String q, int start, int count);
}
