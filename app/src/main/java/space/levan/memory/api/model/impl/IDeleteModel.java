package space.levan.memory.api.model.impl;

import space.levan.memory.api.ApiListener;

/**
 * Created by WangZhiYao on 2017/6/9.
 */

public interface IDeleteModel
{
    void deleteInfo(String isbn, ApiListener apiListener);

    void cancelDelete();
}
