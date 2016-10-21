package space.levan.memory.api;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Api
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public class MemoryApi {

    private static String DOUBAN_ISBN = "https://api.douban.com/v2/book/isbn/";

    public static void getHtml(String strISBN, AsyncHttpResponseHandler handler)
    {
        String urlString = DOUBAN_ISBN + strISBN;
        Client.get(urlString, handler);
    }
}
