package space.levan.memory.api.model;

import space.levan.memory.api.ApiCompleteListener;

/**
 * Created by WangZhiYao on 2016-12-18.
 */

public interface IAddCollectionModel {

    void addCollection(String username, String author, String title,
                        String img, String publisher, String subtitle,
                        String origin_title, String translator, String publish_date,
                        String pages, String isbn, String summary, String review,
                        ApiCompleteListener listener);

    void cancelAdding();
}
