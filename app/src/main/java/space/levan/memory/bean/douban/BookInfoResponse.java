package space.levan.memory.bean.douban;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by WangZhiYao on 2017/4/27.
 */

public class BookInfoResponse implements Serializable
{
    public static final long serialVersionUID = 7060254125600464481L;
    public static final String serialVersionName = "bookInfo";
    private String title;
    private String subtitle;
    private String publisher;
    private String[] author;
    private String[] translator;
    private String authors;
    private String translators;
    private String pubdate;
    private String pages;
    private String origin_title;
    private ImageBean images;
    private String image;
    private String isbn13;
    private String summary;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getAuthors() {
        return Arrays.toString(author).replace("[","").replace("]","");
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTranslators() {
        return Arrays.toString(translator).replace("[","").replace("]","");
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
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

    public String getInfoString()
    {
        if (this.author.length > 0)
        {
            return this.author[0].split("、")[0] + "/" + this.publisher + "/" + this.pubdate;
            //String aut = Arrays.toString(author).replaceFirst("\\[","").split(",")[0];
            //Log.w("WZY", aut.substring(0, aut.length()-1));
            //return aut.substring(0, aut.length()-1) + "/" + this.publisher + "/" + this.pubdate;
        }

        return "-/" + this.publisher + "/" + this.pubdate;
    }

    @Override
    public String toString()
    {
        return "BookInfoResponse{" +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author=" + Arrays.toString(author) +
                ", translator=" + Arrays.toString(translator) +
                ", authors='" + authors + '\'' +
                ", translators='" + translators + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", pages='" + pages + '\'' +
                ", origin_title='" + origin_title + '\'' +
                ", images=" + images +
                ", image='" + image + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
