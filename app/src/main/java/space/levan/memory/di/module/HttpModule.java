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
import space.levan.memory.di.qualifier.DouBanUrl;
import space.levan.memory.di.qualifier.SplashUrl;
import space.levan.memory.model.http.api.DouBanApi;
import space.levan.memory.model.http.api.SplashApi;
import space.levan.memory.utils.NetUtils;

/**
 * Retrofit & OkHttp Module
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

        Interceptor requestInterceptor = chain -> {
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

        Interceptor responseInterceptor = chain -> {
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
                .addInterceptor(requestInterceptor)
                .addNetworkInterceptor(responseInterceptor)
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String baseUrl) {
        return builder.baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @SplashUrl
    Retrofit provideSplashRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, SplashApi.HOST);
    }

    @Provides
    @Singleton
    SplashApi provideSplashService(@SplashUrl Retrofit retrofit) {
        return retrofit.create(SplashApi.class);
    }

    @Provides
    @Singleton
    @DouBanUrl
    Retrofit provideDouBanRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, DouBanApi.HOST);
    }

    @Provides
    @Singleton
    DouBanApi provideDouBanService(@DouBanUrl Retrofit retrofit) {
        return retrofit.create(DouBanApi.class);
    }
}
