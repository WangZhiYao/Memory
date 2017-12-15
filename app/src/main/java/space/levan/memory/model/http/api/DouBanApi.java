package space.levan.memory.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import space.levan.memory.model.bean.douban.BookResult;
import space.levan.memory.model.http.response.DouBanResponse;

/**
 * DouBan api
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public interface DouBanApi {

    String HOST = "https://api.douban.com/v2/book/";

    /**
     * get book info from DouBan api
     *
     * @param q     keywords
     * @param start start with this number of all the result
     * @param count how many result will be returned from the server
     * @return
     */
    @GET("search")
    Flowable<DouBanResponse<BookResult>> getBookData(@Query("q") String q,
                                                     @Query("start") int start,
                                                     @Query("count") int count);
}
