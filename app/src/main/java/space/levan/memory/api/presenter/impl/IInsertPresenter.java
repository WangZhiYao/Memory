package space.levan.memory.api.presenter.impl;

import space.levan.memory.bean.douban.BookInfoResponse;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public interface IInsertPresenter
{
    void insertInfo(BookInfoResponse bookInfoResponse);

    void cancelInsert();
}
