package space.levan.memory.api.model;

import android.util.Log;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import space.levan.memory.api.ApiListener;
import space.levan.memory.api.model.impl.IBookListModel;
import space.levan.memory.api.services.ApiService;
import space.levan.memory.api.services.ServiceFactory;
import space.levan.memory.bean.douban.BaseResponse;
import space.levan.memory.bean.douban.BookListResponse;
import space.levan.memory.common.Constant;

/**
 * Created by WangZhiYao on 2017/4/28.
 */

public class BookListModel implements IBookListModel
{

    @Override
    public void loadBookList(String q, int start, int count, String fields, ApiListener listener)
    {
        ApiService apiService = ServiceFactory.createService(Constant.DOUBAN_URL, ApiService.class);
        apiService.getBookList(q, start, count, fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<BookListResponse>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        if (e instanceof UnknownHostException)
                        {
                            Log.d("WZY", e.getMessage());
                            return;
                        }

                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<BookListResponse> bookListResponseResponse)
                    {
                        if (bookListResponseResponse.isSuccessful())
                        {
                            listener.onComplete(bookListResponseResponse.body());
                        }
                        else
                        {
                            listener.onFailed(new BaseResponse(bookListResponseResponse.code(), bookListResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading()
    {

    }
}
