package space.levan.memory.api.client;

import com.loopj.android.http.JsonHttpResponseHandler;

import space.levan.memory.common.URL;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class MemoryApi {

    //private static String fields = "title,author,translator,publisher,pubdate,summary,pages,price,isbn13,images";

    public static void getBookList(String KeyWords, int start, int count, String fields, JsonHttpResponseHandler handler)
    {
        String urlString = URL.HOST_URL_DOUBAN + "?q=" + KeyWords + "&start=" + start + "&count=" + count + "&fields=" + fields;
        Client.get(urlString, handler);
    }
}
