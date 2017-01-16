package space.levan.memory.bean;

import io.realm.RealmObject;

/**
 * Created by WangZhiYao on 2017/1/9.
 */

public class Book extends RealmObject {


    /**
     * title : string
     * subtitle : string
     * origin_title : string
     * publisher : string
     * author : string
     * translator : string
     * pubdate : string
     * pages : string
     * img : string
     * isbn : string
     * summary : string
     */

    private String title;
    private String subtitle;
    private String origin_title;
    private String publisher;
    private String author;
    private String translator;
    private String pubdate;
    private String pages;
    private String img;
    private String isbn;
    private String summary;
    private String user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getInfoString() {
        if (this.author.isEmpty())
        {
            return "-";
        }
        return this.author + "/" + this.publisher + "/" + this.pubdate;

    }
}
