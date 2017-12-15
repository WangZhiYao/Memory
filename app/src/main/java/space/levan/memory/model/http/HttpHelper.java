package space.levan.memory.model.http;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.model.bean.splash.Splash;
import space.levan.memory.model.http.response.DouBanResponse;
import space.levan.memory.model.http.response.UnSplashResponse;

/**
 * HttpHelper interface class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface HttpHelper {

    /**
     * get Splash Image from UnSplash api
     *
     * @param scrWidth  screen width
     * @param scrHeight screen height
     * @return
     */
    Flowable<UnSplashResponse<Splash>> getSplashData(int scrWidth, int scrHeight);

    /**
     * get book info from DouBan api
     *
     * @param q     keywords
     * @param start start with this number of all the result
     * @param count how many result will be returned from the server
     * @return
     */
    Flowable<DouBanResponse<BookResult>> getBookData(String q, int start, int count);
}
