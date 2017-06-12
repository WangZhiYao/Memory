package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;
import space.levan.memory.bean.douban.BookInfoResponse;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public interface IInsertModel
{
    void insertInfo(BookInfoResponse bookInfoResponse, ApiListener apiListener);

    void cancelInsert();
}
