package space.levan.memory.api.presenter;

/**
 * Created by WangZhiYao on 2016-12-18.
 */

public interface IAddCollectionPresenter {

    void addCollection(String username, String author, String title,
                       String img, String publisher, String subtitle,
                       String origin_title, String translator, String publish_date,
                       String pages, String isbn, String summary, String review);

    void cancelAdding();
}
