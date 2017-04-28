package space.levan.memory.api;

import space.levan.memory.bean.douban.BaseResponse;

/**
 * Created by WangZhiYao on 2017/4/13.
 */

public interface ApiListener
{
    void onComplete(Object result);

    void onFailed(BaseResponse msg);
}
