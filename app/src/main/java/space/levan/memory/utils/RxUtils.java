package space.levan.memory.utils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
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
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Unified result processing from UnSplash Api
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<UnSplashResponse<T>, T> handleUnSplashResult() {
        return new FlowableTransformer<UnSplashResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<UnSplashResponse<T>> upstream) {
                return upstream.flatMap(new Function<UnSplashResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(UnSplashResponse<T> tUnSplashResponse) throws Exception {
                        if (tUnSplashResponse.getResult() != null) {
                            return createData(tUnSplashResponse.getResult());
                        } else {
                            return Flowable.error(new ApiException("空"));
                        }
                    }
                });
            }
        };
    }

    /**
     * Unified result processing from DouBan Api
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<DouBanResponse<T>, T> handleDouBanResult() {
        return new FlowableTransformer<DouBanResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<DouBanResponse<T>> upstream) {
                return upstream.flatMap(new Function<DouBanResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(DouBanResponse<T> tDouBanResponse) throws Exception {
                        if (tDouBanResponse.getResults() != null) {
                            return createData(tDouBanResponse.getResults());
                        } else {
                            return Flowable.error(new ApiException(tDouBanResponse.getMsg(), tDouBanResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * Convert generic to Flowable
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    //emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
