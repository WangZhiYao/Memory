package space.levan.memory.api.client;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class MemoryApi {

    private static String DOUBAN_ISBN = "https://api.douban.com/v2/book/isbn/";

    public static void getBookDetail(String strISBN, AsyncHttpResponseHandler handler)
    {
        String urlString = DOUBAN_ISBN + strISBN;
        Client.get(urlString, handler);
    }
}
