package space.levan.memory.api;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import io.reactivex.SingleObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import space.levan.memory.api.exception.ApiException;
import space.levan.memory.api.response.BaseResponse;
import space.levan.memory.log.Logger;

/**
 * @author WangZhiYao
 * @date 2019/7/1
 */
public abstract class ApiSubscriber<T> implements SingleObserver<T> {

    private static final String TAG = "ApiSubscriber";

    @Override
    public void onError(Throwable e) {

        if (e instanceof HttpException) {
            int httpCode = ((HttpException) e).code();
            if (httpCode == ApiException.HTTP_BAD_REQUEST
                    || httpCode == ApiException.HTTP_UNAUTHORIZED
                    || httpCode == ApiException.HTTP_FORBIDDEN
                    || httpCode == ApiException.HTTP_NOT_FOUND
                    || httpCode == ApiException.HTTP_SERVER_ERROR) {
                Response response = ((HttpException) e).response();
                if (response != null) {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            String responseBodyStr = responseBody.string();
                            BaseResponse baseResponse = new Gson().fromJson(responseBodyStr, BaseResponse.class);
                            if (baseResponse != null) {
                                onError(new ApiException(baseResponse.getCode()));
                                return;
                            }
                        } catch (Exception ex) {
                            Logger.e(TAG, response.toString());
                        }
                    }
                }
            }

            onError(new ApiException(httpCode));
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            onError(new ApiException(ApiException.JSON_EXCEPTION));
        } else {
            onError(new ApiException(ApiException.UNKNOWN_EXCEPTION));
        }
    }

    public abstract void onError(ApiException ex);
}
