package space.levan.memory.api.view;

/**
 * Created by WangZhiYao on 2017/4/28.
 */

public interface IBookListView extends IBaseView
{
    void refreshData(Object result);

    void addData(Object result);
}
