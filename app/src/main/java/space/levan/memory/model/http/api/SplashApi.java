package space.levan.memory.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import space.levan.memory.model.bean.splash.SplashBean;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface SplashApi {

    String HOST = "https://api.unsplash.com/";
    String CLIENT_ID = "e14a9d2f95c57d0e1312e50919dcc935a8723ef94ee9ed029de48ca77b2cd342";

    /**
     * get a random Splash Image from UnSplash.com depends on screen width and height
     *
     * @param cid client id from UnSplash.com
     * @param width screen width
     * @param height screen height
     * @return
     */
    @GET("photos/random")
    Flowable<SplashBean> getSplashData(@Query("client_id") String cid,
                                       @Query("w") int width,
                                       @Query("h") int height);
}
