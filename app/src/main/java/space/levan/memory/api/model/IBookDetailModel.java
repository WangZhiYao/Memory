package space.levan.memory.api.model;

import space.levan.memory.api.ApiCompleteListener;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public interface IBookDetailModel {

    void getBookDetail(String urlString, ApiCompleteListener listener);
}
