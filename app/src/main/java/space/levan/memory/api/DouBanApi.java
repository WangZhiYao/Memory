package space.levan.memory.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import space.levan.memory.api.response.BookResponse;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public interface DouBanApi {

    String BASE_URL = "https://api.douban.com/v2/";

    /**
     * 查询书籍信息
     *
     * @param keywords 关键字
     * @param start    从0开始
     * @param count    每页加载数量
     * @param apiKey   douban api key
     * @return
     */
    @GET("book/search")
    Single<BookResponse> queryBookByKeywords(@Query("q") String keywords,
                                             @Query("startForResult") int start,
                                             @Query("count") int count,
                                             @Query("apikey") String apiKey);
}
