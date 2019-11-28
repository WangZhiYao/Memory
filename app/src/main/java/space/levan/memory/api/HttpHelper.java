package space.levan.memory.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import space.levan.memory.log.Logger;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class HttpHelper {

    private static final String TAG = "HttpHelper";
    private static final int DEFAULT_TIMEOUT_CONNECT = 10;
    private static final int DEFAULT_READ_CONNECT = 10;
    private static final int DEFAULT_WRITE_CONNECT = 10;

    private DouBanApi mDouBanApi;

    private HttpHelper() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(s -> Logger.d(TAG, s));
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_CONNECT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_CONNECT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DouBanApi.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mDouBanApi = retrofit.create(DouBanApi.class);
    }

    private static class SingletonHolder {
        private static HttpHelper mInstance = new HttpHelper();
    }

    public static HttpHelper getInstance() {
        return SingletonHolder.mInstance;
    }


    public DouBanApi getDouBanApi() {
        return mDouBanApi;
    }
}
