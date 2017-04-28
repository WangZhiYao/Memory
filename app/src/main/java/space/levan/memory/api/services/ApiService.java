package space.levan.memory.api.services;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import space.levan.memory.bean.douban.BookListResponse;

/**
 * Created by WangZhiYao on 2017/4/27.
 */

public interface ApiService
{
    @GET("search")
    Observable<Response<BookListResponse>> getBookList(@Query("q") String q,
                                                       @Query("start") int start,
                                                       @Query("count") int count,
                                                       @Query("fields") String fields);
}
