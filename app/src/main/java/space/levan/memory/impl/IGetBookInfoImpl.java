package space.levan.memory.impl;

import space.levan.memory.model.OnGetBookInfoListener;

/**
 * 获取图书信息接口
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public interface IGetBookInfoImpl {

    void getBookInfo(String urlString, OnGetBookInfoListener listener);
    void getBookImg(String urlString, OnGetBookInfoListener listener);
}
