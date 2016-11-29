package space.levan.memory.api.model.impl;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.client.MemoryApi;
import space.levan.memory.api.model.IBookListModel;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public class BookListModelImpl implements IBookListModel {

    @Override
    public void getBookList(String KeyWords, int start, int count, ApiCompleteListener listener)
    {
        MemoryApi.getBookList(KeyWords, start , count, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
            {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
