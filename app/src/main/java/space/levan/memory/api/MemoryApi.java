package space.levan.memory.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;

/**
 * Api
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public class MemoryApi {

    private static String DOUBAN_ISBN = "https://api.douban.com/v2/book/isbn/";

    public static void getBookInfo(String strISBN, AsyncHttpResponseHandler handler)
    {
        String urlString = DOUBAN_ISBN + strISBN;
        Client.get(urlString, handler);
    }

    public static void getBookImg(String strImgUrl, BinaryHttpResponseHandler bHandler)
    {
        Client.get(strImgUrl, bHandler);
    }
}
