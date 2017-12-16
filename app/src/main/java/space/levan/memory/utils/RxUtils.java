package space.levan.memory.utils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import space.levan.memory.model.http.exception.ApiException;
import space.levan.memory.model.http.response.DouBanResponse;
import space.levan.memory.model.http.response.UnSplashResponse;

/**
 * RxUtils class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class RxUtils {

    /**
     * Unified thread processing
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Unified result processing from UnSplash Api
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<UnSplashResponse<T>, T> handleUnSplashResult() {
        return upstream -> upstream.flatMap((Function<UnSplashResponse<T>, Flowable<T>>) tUnSplashResponse -> {
            //if (tUnSplashResponse.getResult() instanceof Splash) {
            //
            //} else {
            //    return Flowable.error(new ApiException(tUnSplashResponse.getErrors().get(0)));
            //}
            return createData(tUnSplashResponse.getResult());
        });
    }

    /**
     * Unified result processing from DouBan Api
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<DouBanResponse<T>, T> handleDouBanResult() {
        return upstream -> upstream.flatMap((Function<DouBanResponse<T>, Flowable<T>>) tDouBanResponse -> {
            if (!tDouBanResponse.getRequest().isEmpty()) {
                return createData(tDouBanResponse.getResults());
            } else {
                return Flowable.error(new ApiException(tDouBanResponse.getMsg(), tDouBanResponse.getCode()));
            }
        });
    }

    /**
     * Convert generic to Flowable
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(final T t) {
        return Flowable.create(e -> {
            try {
                e.onNext(t);
                e.onComplete();
            } catch (Exception e1) {
                e.onError(e1);
            }
        }, BackpressureStrategy.BUFFER);
    }
}
