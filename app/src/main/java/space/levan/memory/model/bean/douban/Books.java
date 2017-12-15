package space.levan.memory.model.bean.douban;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Book info entity
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class Books extends RealmObject {

    private String subtitle;
    private RealmList<String> author = new RealmList<>();
    private String pubdate;
    private RealmList<Tags> tags;
    private String origin_title;
    private String image;
    private String binding;
    private RealmList<String> translator = new RealmList<>();
    private String catalog;
    private String pages;
    private Images images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(RealmList<String> author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(RealmList<Tags> tags) {
        this.tags = tags;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getBinding() {
        return binding;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(RealmList<String> translator) {
        this.translator = translator;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Images getImages() {
        return images;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAlt() {
        return alt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "subtitle='" + subtitle + '\'' +
                ", author=" + author +
                ", pubdate='" + pubdate + '\'' +
                ", tags=" + tags.toString() +
                ", origin_title='" + origin_title + '\'' +
                ", image='" + image + '\'' +
                ", binding='" + binding + '\'' +
                ", translator=" + translator +
                ", catalog='" + catalog + '\'' +
                ", pages='" + pages + '\'' +
                ", images=" + images.toString() +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", alt_title='" + alt_title + '\'' +
                ", author_intro='" + author_intro + '\'' +
                ", summary='" + summary + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
