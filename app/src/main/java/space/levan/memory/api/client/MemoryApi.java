package space.levan.memory.api.client;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class MemoryApi {

    private static String DOUBAN_ISBN = "https://api.douban.com/v2/book/isbn/";
    private static String DOUBAN_BOOK = "https://api.douban.com/v2/book/search";

    public static void getBookDetail(String strISBN, AsyncHttpResponseHandler handler)
    {
        String urlString = DOUBAN_ISBN + strISBN;
        Client.get(urlString, handler);
    }

    public static void getBookList(String KeyWords, int start, int count, JsonHttpResponseHandler handler)
    {
        String urlString = DOUBAN_BOOK + "?q=" + KeyWords + "&start=" + start + "&count=" + count;
        Client.get(urlString, handler);
    }
}
