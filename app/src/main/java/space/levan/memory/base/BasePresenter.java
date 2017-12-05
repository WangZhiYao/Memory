package space.levan.memory.base;

/**
 * BasePresenter
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface BasePresenter<T extends BaseView> {

    /**
     * attach with view which extends BaseView
     *
     * @param view view which extends BaseView
     */
    void attachView(T view);

    /**
     * detach with view which extends BaseView
     */
    void detachView();
}
