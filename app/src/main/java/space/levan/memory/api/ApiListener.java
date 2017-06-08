package space.levan.memory.api;

/**
 * Created by WangZhiYao on 2017/4/13.
 */

public interface ApiListener
{
    void onSuccess(Object result);

    void onFailure(String msg);
}
