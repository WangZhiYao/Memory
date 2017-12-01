package space.levan.memory.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import space.levan.memory.model.bean.douban.BookResult;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public interface DouBanApi {

    String HOST = "https://api.douban.com/v2/book/";

    @GET("search")
    Flowable<BookResult> getBookData(@Query("q") String q,
                                     @Query("start") int start,
                                     @Query("count") int count);
}
