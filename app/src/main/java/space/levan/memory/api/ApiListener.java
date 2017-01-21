package space.levan.memory.api;

import space.levan.memory.bean.BaseResponse;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface ApiListener {

    void onComplete(Object result);

    void onFailed(BaseResponse msg);
}
