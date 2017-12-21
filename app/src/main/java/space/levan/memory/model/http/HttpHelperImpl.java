package space.levan.memory.model.http;

import javax.inject.Inject;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.model.bean.splash.Splash;
import space.levan.memory.model.http.api.DouBanApi;
import space.levan.memory.model.http.api.SplashApi;
import space.levan.memory.model.http.response.UnSplashResponse;

/**
 * HttpHelper interface implementation class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class HttpHelperImpl implements HttpHelper {

    private SplashApi mSplashApi;
    private DouBanApi mDouBanApi;

    @Inject
    public HttpHelperImpl(SplashApi splashApi, DouBanApi douBanApi) {
        this.mSplashApi = splashApi;
        this.mDouBanApi = douBanApi;
    }

    @Override
    public Flowable<UnSplashResponse<Splash>> getSplashData(int scrWidth, int scrHeight) {
        return mSplashApi.getSplashData(SplashApi.CLIENT_ID, scrWidth, scrHeight);
    }

    @Override
    public Flowable<BookResult> getBookData(String q, int start, int count) {
        return mDouBanApi.getBookData(q, start, count);
    }
}
