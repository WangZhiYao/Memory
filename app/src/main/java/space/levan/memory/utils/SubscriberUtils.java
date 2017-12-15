package space.levan.memory.utils;

import android.text.TextUtils;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;
import space.levan.memory.base.BaseView;
import space.levan.memory.model.http.exception.ApiException;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/12/16
 */

public class SubscriberUtils<T> extends ResourceSubscriber<T> {

    private BaseView mView;
    private String mErrorMsg;

    protected SubscriberUtils(BaseView view) {
        this.mView = view;
    }

    protected SubscriberUtils(BaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showMessage(mErrorMsg);
        } else if (t instanceof ApiException) {
            mView.showMessage(t.toString());
        } else if (t instanceof HttpException) {
            mView.showMessage("数据加载失败");
        } else {
            mView.showMessage("未知错误");
        }
    }
}
