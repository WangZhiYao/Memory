package space.levan.memory.api;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public interface ApiCompleteListener {

    void Success(Object response);
    void Failure(String msg);
}
