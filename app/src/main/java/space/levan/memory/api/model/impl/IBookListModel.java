package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public interface IBookListModel {

    void loadBookList(String q, int start, int count, String fields, ApiListener listener);

    void cancelLoading();
}
