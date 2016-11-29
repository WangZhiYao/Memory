package space.levan.memory.api.model;

import space.levan.memory.api.ApiCompleteListener;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public interface IBookListModel {

    void getBookList(String KeyWords, int start, int count, ApiCompleteListener listener);
}
