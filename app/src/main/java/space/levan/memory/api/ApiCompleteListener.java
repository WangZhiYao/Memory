package space.levan.memory.api;

import space.levan.memory.bean.BaseResponse;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public interface ApiCompleteListener {

    void onComplected(Object result);

    void onFailed(BaseResponse msg);
}
