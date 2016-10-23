package space.levan.memory.api.model.impl;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.client.MemoryApi;
import space.levan.memory.api.model.IBookDetailModel;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class BookDetailModelImpl implements IBookDetailModel {

    @Override
    public void getBookDetail(String urlString, ApiCompleteListener listener)
    {
        MemoryApi.getBookDetail(urlString, new JsonHttpResponseHandler()
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
