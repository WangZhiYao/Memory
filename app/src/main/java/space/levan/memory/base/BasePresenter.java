package space.levan.memory.base;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
