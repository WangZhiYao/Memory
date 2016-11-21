package space.levan.memory.api.model.impl;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.client.MemoryApi;
import space.levan.memory.api.model.IBookDetailModel;
import space.levan.memory.bean.http.douban.Book;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class BookDetailModelImpl implements IBookDetailModel {

    private Book mBook;
    private HashMap<String, Object> mBookDetail;

    @Override
    public void getBookDetail(String urlString, ApiCompleteListener listener)
    {
        MemoryApi.getBookDetail(urlString, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                mBook = new Gson().fromJson(response.toString(), Book.class);
                mBookDetail = new HashMap<>();
                mBookDetail.put("bookTitle", mBook.getTitle());
                mBookDetail.put("bookISBN13", mBook.getIsbn13());
                mBookDetail.put("bookAuthor", mBook.getAuthor());
                mBookDetail.put("bookPubDate", mBook.getPubdate());
                mBookDetail.put("bookSummary", mBook.getSummary());
                mBookDetail.put("bookPublisher", mBook.getPublisher());
                mBookDetail.put("bookTranslator", mBook.getTranslator());
                mBookDetail.put("bookImg", mBook.getImages().getLarge());
                listener.Success(mBookDetail);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
            {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
