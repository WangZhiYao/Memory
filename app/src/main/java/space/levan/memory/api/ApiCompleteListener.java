package space.levan.memory.api;

import java.util.HashMap;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public interface ApiCompleteListener {

    void Success(HashMap<String, Object> mBook);
    void Failure(String response);
}
