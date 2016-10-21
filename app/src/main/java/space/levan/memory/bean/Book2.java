package space.levan.memory.bean;

/**
 * 数据类型转换
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public class Book2 {

    /**
     * id : long
     * pubdate : BeJson
     * image : http://www.bejson.com
     * pages : h
     * publisher : s
     * isbn13 : isbn13
     * author : author
     * tags : tags
     * translator : translator
     */

    private Long id;
    private String pubdate;
    private String image;
    private String pages;
    private String publisher;
    private String isbn13;
    private String author;
    private String tags;
    private String title;
    private String translator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public Book2(Long id, String title, String author, String publisher)
    {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public Book2(String title, String author, String publisher)
    {
        super();
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }
}
