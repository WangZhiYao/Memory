package space.levan.memory.implement;

import space.levan.memory.model.OnGetBookInfoListener;

/**
 * 获取图书信息接口
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public interface GetBookInfoImplement {

    void getBookInfo(String urlString, OnGetBookInfoListener listener);
}
