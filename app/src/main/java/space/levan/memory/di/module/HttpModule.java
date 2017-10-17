package space.levan.memory.di.module;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import space.levan.memory.app.Constants;
import space.levan.memory.utils.NetUtils;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@Module
public class HttpModule {

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 20);

        Interceptor REQUEST_INTERCEPTOR = chain -> {
            Request request = chain.request();
            int maxState = 60 * 60;
            CacheControl tempCacheControl = new CacheControl.Builder()
                    .maxStale(maxState, TimeUnit.MINUTES)
                    .build();
            request = request.newBuilder()
                    .cacheControl(tempCacheControl)
                    .build();

            return chain.proceed(request);
        };

        Interceptor RESPONSE_INTERCEPTOR = chain -> {
            Request request = chain.request();
            Response oriResponse = chain.proceed(request);
            int maxAge;
            if (!NetUtils.isNetConnected()) {
                maxAge = 60 * 60 * 24;
            } else {
                maxAge = 60;
            }
            return oriResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        };

        return builder
                //.addInterceptor(REQUEST_INTERCEPTOR)
                //.addNetworkInterceptor(RESPONSE_INTERCEPTOR)
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder.baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
