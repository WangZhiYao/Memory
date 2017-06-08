package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017/4/28.
 */

public interface IBookListModel
{
    /**
     * 获取图书接口
     */
    void loadBookList(String q, int start, int count, String fields, ApiListener apiListener);

    /**
     * 取消加载数据
     */
    void cancelLoading();
}
