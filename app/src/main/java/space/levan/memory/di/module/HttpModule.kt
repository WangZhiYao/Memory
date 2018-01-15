package space.levan.memory.di.module

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import space.levan.memory.Constants
import space.levan.memory.utils.NetUtils
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

@Module
class HttpModule {

    @Provides
    @Singleton
    internal fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        val cacheFile = File(Constants.PATH_DATA)
        val cache = Cache(cacheFile, 1024 * 1024 * 20)

        var requestInterceptor = Interceptor { chain ->
            var request = chain.request()
            val maxState = 60 * 60
            val tempCacheControl = CacheControl.Builder()
                    .maxStale(maxState, TimeUnit.MINUTES)
                    .build()
            request = request.newBuilder()
                    .cacheControl(tempCacheControl)
                    .build()

            chain.proceed(request)
        }

        var responseInterceptor = Interceptor { chain ->
            val request = chain.request()
            val oriResponse = chain.proceed(request)
            val maxAge: Int = if (!NetUtils.isNetConnected) 60 * 60 * 24 else 60

            oriResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build()
        }

        return builder
                //.addInterceptor(requestInterceptor)
                //.addNetworkInterceptor(responseInterceptor)
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    private fun createRetrofit(builder: Retrofit.Builder, client: OkHttpClient, baseUrl: String): Retrofit {
        return builder.baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
