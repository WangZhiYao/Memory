package space.levan.memory.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import space.levan.memory.api.MemoryApi;
import space.levan.memory.bean.Book;
import space.levan.memory.impl.IGetBookInfoImpl;

/**
 * 利用豆瓣API查询ISBN码对应图书信息
 *
 * Created by WangZhiYao on 2016/10/20.
 */

public class GetBookInfo implements IGetBookInfoImpl {

    private Book book;

    @Override
    public void getBookInfo(String urlString, final OnGetBookInfoListener listener)
    {
        MemoryApi.getBookInfo(urlString, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                book = new Gson().fromJson(response.toString(), Book.class);
                String bookName = book.getTitle();
                List<String> bookAuthors = book.getAuthor();
                String bookAuthor = "";
                for (String temp : bookAuthors)
                {
                    bookAuthor = bookAuthor.concat(temp).concat(" ");
                }
                String bookPublisher = book.getPublisher();
                String bookImg = book.getImages().getLarge();
                listener.onGetInfoSuccess(bookName + "," +bookAuthor + "," + bookPublisher + "," + bookImg);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
            {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                listener.onGetInfoFailure("没有查询到此书的相关信息，请手动输入");
            }
        });
    }

    @Override
    public void getBookImg(String urlString, final OnGetBookInfoListener listener)
    {
        MemoryApi.getBookImg(urlString, new BinaryHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData)
            {
                Bitmap bookImg = BitmapFactory.decodeByteArray(binaryData, 0, binaryData.length);
                listener.onGetImgSuccess(bookImg);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error)
            {
                listener.onGetImgFailure("获取封面失败");
            }
        });
    }
}
