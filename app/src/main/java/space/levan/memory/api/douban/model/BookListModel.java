package space.levan.memory.api.douban.model;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.net.UnknownHostException;

import cz.msebera.android.httpclient.Header;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.client.MemoryApi;
import space.levan.memory.api.douban.model.impl.IBookListModel;
import space.levan.memory.bean.BaseResponse;
import space.levan.memory.bean.douban.BookListResponse;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public class BookListModel implements IBookListModel {

    private BookListResponse mBookListResponse;

    @Override
    public void loadBookList(String q, int start, int count, String fields, ApiCompleteListener listener)
    {
        MemoryApi.getBookList(q, start, count, fields, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
                mBookListResponse = new Gson().fromJson(response.toString(), BookListResponse.class);
                listener.onComplected(mBookListResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
            {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (throwable instanceof UnknownHostException)
                {
                    listener.onFailed(null);
                    return;
                }
                listener.onFailed(new BaseResponse(statusCode, throwable.getMessage()));
            }
        });
    }

    @Override
    public void cancelLoading()
    {
        // TODO: 2016-12-18 取消接口调用
    }
}
