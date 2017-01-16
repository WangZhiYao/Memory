package space.levan.memory.bean.douban;

import java.io.Serializable;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public class BookInfoResponse implements Serializable {

    public static final String serialVersionName = "bookInfo";
    private String title;
    private String subtitle;
    private String origin_title;
    private String publisher;
    private String[] author;
    private String[] translator;
    private String pubdate;
    private String pages;
    private ImageBean images;
    private String isbn13;
    private String summary;
    private String price;

    public static String getSerialVersionName() {
        return serialVersionName;
    }

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

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String[] getTranslator() {
        return translator;
    }

    public void setTranslator(String[] translator) {
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

    public ImageBean getImages() {
        return images;
    }

    public void setImages(ImageBean images) {
        this.images = images;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfoString() {
        if (this.author.length > 0) {
            return this.author[0].split("、")[0] + "/" + this.publisher + "/" + this.pubdate;
        }
        return "-";
    }
}
