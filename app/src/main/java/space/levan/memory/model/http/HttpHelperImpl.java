package space.levan.memory.model.http;

import javax.inject.Inject;

import io.reactivex.Flowable;
import space.levan.memory.model.bean.SplashBean;
import space.levan.memory.model.http.api.SplashApi;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class HttpHelperImpl implements HttpHelper {

    private SplashApi mSplashApi;

    @Inject
    public HttpHelperImpl(SplashApi splashApi) {
        this.mSplashApi = splashApi;
    }

    @Override
    public Flowable<SplashBean> getSplashData(int scrWidth, int scrHeight) {
        return mSplashApi.getSplashData(SplashApi.CLIENT_ID, scrWidth, scrHeight);
    }
}
