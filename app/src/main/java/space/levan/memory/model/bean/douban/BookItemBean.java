package space.levan.memory.model.bean.douban;

import java.util.List;

import space.levan.memory.utils.StringUtils;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class BookItemBean {

    /**
     * rating : {"max":10,"numRaters":1,"average":"0.0","min":0}
     * subtitle : 计算机
     * author : ["于今昌 编"]
     * pubdate : 2007-12
     * tags : []
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s5890592.jpg
     * binding :
     * translator : []
     * catalog :
     * pages : 142
     * images : {"small":"https://img3.doubanio.com/spic/s5890592.jpg","large":"https://img3.doubanio.com/lpic/s5890592.jpg","medium":"https://img3.doubanio.com/mpic/s5890592.jpg"}
     * alt : https://book.douban.com/subject/3049219/
     * id : 3049219
     * publisher : 吉林出版集团有限责任公司
     * isbn10 : 7807621834
     * isbn13 : 9787807621836
     * title : 计算机
     * url : https://api.douban.com/v2/book/3049219
     * alt_title :
     * author_intro :
     * summary : 《走进新科学:计算机》共十二本，内容广泛。包括宇宙、航天、地球、海洋、生命、生物工程、交通、能源、自然资源、环境、电脑、计算机等多个学科。该丛书是由各个学科的专家、学者和科普作家合力编撰的，他们在总结前人经验的基础上，对各学科知识进行了严格的、系统的分类，再从数以千万计的资料中选择最新的、最科学的、最准确的诠释，用简明易懂、生动有趣的语言表述出来，并配有青少年喜闻乐见的卡通漫画，真正带给青少年一个对科普知识解读的全新角度，并从中体会到获得知识的乐趣。
     * 人类在不断地进步，科学在迅猛地发展，未来的社会更是一个知识的社会。一个自主自强的民族是和先进的科学技术分不开的，在青少年中普及科学知识，尤其是最新的科学知识，并把它运用到未来的实践中去，以我们不懈的努力造就一批杰出科技人才，奉献于国家、奉献于社会，这是我们追求的目标，也是我们努力工作的动力。
     * price : 8.00元
     */

    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
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
    private List<String> author;
    private List<String> tags;
    private List<String> translator;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
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

    public String getAuthor() {
        return StringUtils.listToString(author);
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }
}
