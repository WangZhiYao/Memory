package space.levan.memory.bean.realm;

import io.realm.RealmObject;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class Book extends RealmObject
{
    public String isbn13;
    public String title;
    public String subtitle;
    public String publisher;
    public String authors;
    public String translators;
    public String pubdate;
    public String pages;
    public String origin_title;
    public String image;
    public String summary;
    public String infoString;
    public String user;
}
