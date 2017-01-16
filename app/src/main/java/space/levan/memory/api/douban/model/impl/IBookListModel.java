package space.levan.memory.api.douban.model.impl;

import space.levan.memory.api.ApiCompleteListener;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public interface IBookListModel {
    /**
     * 获取图书接口
     */
    void loadBookList(String q, int start, int count, String fields, ApiCompleteListener listener);

    /**
     * 取消加载数据
     */
    void cancelLoading();
}
