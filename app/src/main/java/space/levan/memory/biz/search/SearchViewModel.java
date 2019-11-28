package space.levan.memory.biz.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import java.util.Collections;

import io.reactivex.disposables.Disposable;
import space.levan.memory.Constants;
import space.levan.memory.api.ApiResponse;
import space.levan.memory.api.ApiSubscriber;
import space.levan.memory.api.HttpHelper;
import space.levan.memory.api.exception.ApiException;
import space.levan.memory.api.model.Book;
import space.levan.memory.api.response.BookResponse;
import space.levan.memory.utils.RxUtils;

/**
 * @author WangZhiYao
 * @date 2019/7/4
 */
public class SearchViewModel extends AndroidViewModel {

    private PagedList.Config mPagedListConfig;

    private MutableLiveData<ApiResponse> mLoadInitialStatus;
    private MutableLiveData<ApiResponse> mLoadRangeStatus;

    public SearchViewModel(@NonNull Application application) {
        super(application);

        mPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(3)
                .build();

        mLoadInitialStatus = new MutableLiveData<>();
        mLoadRangeStatus = new MutableLiveData<>();
    }

    public LiveData<PagedList<Book>> queryBookByKeywords(String keywords) {
        return new LivePagedListBuilder<>(new SearchResultDataSourceFactory(keywords), mPagedListConfig)
                .build();
    }

    public LiveData<ApiResponse> getLoadInitialStatus() {
        return mLoadInitialStatus;
    }

    public LiveData<ApiResponse> getLoadRangeStatus() {
        return mLoadRangeStatus;
    }

    class SearchResultDataSourceFactory extends PositionalDataSource.Factory<Integer, Book> {

        private String mKeywords;

        SearchResultDataSourceFactory(String keywords) {
            mKeywords = keywords;
        }

        @NonNull
        @Override
        public DataSource<Integer, Book> create() {
            return new SearchResultDataSource(mKeywords);
        }
    }

    class SearchResultDataSource extends PositionalDataSource<Book> {

        private String mKeywords;

        SearchResultDataSource(String keywords) {
            mKeywords = keywords;
        }

        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Book> callback) {
            HttpHelper.getInstance().getDouBanApi()
                    .queryBookByKeywords(mKeywords, params.requestedStartPosition, params.requestedLoadSize, Constants.DOUBAN_API_KEY)
                    .compose(RxUtils.rxSchedulerHelper())
                    .subscribe(new ApiSubscriber<BookResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mLoadInitialStatus.postValue(new ApiResponse.Loading());
                        }

                        @Override
                        public void onSuccess(BookResponse bookResponse) {
                            mLoadInitialStatus.setValue(new ApiResponse.Success());
                            if (bookResponse != null && bookResponse.getBooks() != null) {
                                callback.onResult(bookResponse.getBooks(), params.requestedStartPosition);
                            } else {
                                callback.onResult(Collections.emptyList(), params.requestedStartPosition);
                            }
                        }

                        @Override
                        public void onError(ApiException ex) {
                            mLoadInitialStatus.setValue(new ApiResponse.Error(ex.getCode()));
                            callback.onResult(Collections.emptyList(), 0);
                        }
                    });
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Book> callback) {
            HttpHelper.getInstance().getDouBanApi()
                    .queryBookByKeywords(mKeywords, params.startPosition, params.loadSize, Constants.DOUBAN_API_KEY)
                    .compose(RxUtils.rxSchedulerHelper())
                    .subscribe(new ApiSubscriber<BookResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mLoadRangeStatus.postValue(new ApiResponse.Loading());
                        }

                        @Override
                        public void onSuccess(BookResponse bookResponse) {
                            mLoadRangeStatus.postValue(new ApiResponse.Success());
                            if (bookResponse != null && bookResponse.getBooks() != null
                                    && params.startPosition < bookResponse.getTotal()) {
                                callback.onResult(bookResponse.getBooks());
                            } else {
                                callback.onResult(Collections.emptyList());
                            }
                        }

                        @Override
                        public void onError(ApiException ex) {
                            mLoadRangeStatus.postValue(new ApiResponse.Error(ex.getCode()));
                            callback.onResult(Collections.emptyList());
                        }
                    });
        }
    }
}
