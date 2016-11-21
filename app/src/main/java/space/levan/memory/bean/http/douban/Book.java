package space.levan.memory.bean.http.douban;

import java.util.List;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class Book {

    /**
     * msg : book_not_found
     * rating : {"max":10,"numRaters":24,"average":"7.6","min":0}
     * subtitle : 第一部反映中国审计的长篇小说，揭开审计的神秘面纱，震撼国人的警世之作，同名20集电视连续剧，央视黄金档热播！
     * author : ["项俊波"]
     * pubdate : 2006-1
     * tags : [{"count":16,"name":"审计","title":"审计"},{"count":6,"name":"小说","title":"小说"},{"count":4,"name":"官场","title":"官场"},{"count":2,"name":"政府审计","title":"政府审计"},{"count":2,"name":"职场小说","title":"职场小说"},{"count":2,"name":"内部审计","title":"内部审计"},{"count":2,"name":"会计","title":"会计"},{"count":1,"name":"经济学","title":"经济学"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s9127731.jpg
     * binding : 简裝本
     * translator : []
     * catalog :
     * pages : 353
     * images : {"small":"https://img3.doubanio.com/spic/s9127731.jpg","large":"https://img3.doubanio.com/lpic/s9127731.jpg","medium":"https://img3.doubanio.com/mpic/s9127731.jpg"}
     * alt : https://book.douban.com/subject/1458726/
     * id : 1458726
     * publisher : 百花文艺出版社
     * isbn10 : 7530643924
     * isbn13 : 9787530643921
     * title : 审计报告
     * url : https://api.douban.com/v2/book/1458726
     * alt_title :
     * author_intro : 项俊波（笔名纯钢），北京大学法学博士。中国作家协会会员，曾在审计系统工作二十余年，现供职于中国人民银行。多年来在《人民日报》、《光明日报》、《中国电视》、《现代作家》、《江南》、《山西文学》等报刊杂志上发表诗歌、散文、中短篇小说、报告文学多篇，并创作和发表40集电视连续剧《曾国藩》、60 集电视连续剧《紫剑传奇》、6集电视连续剧《人民不会忘记》、8集电视连续剧《裂缝》、20集电视连续剧《审计报告》、电影文学剧本《远山》、《石门寨的女人》等。其中《远山》、《人民不会忘记》、《裂缝》、《审计报告》由中央电视台摄制完成播出后，在社会上引起了广泛关注。8集电视连续剧《裂缝》荣获 1999年全国电视连续剧飞天奖一等奖。
     * summary : 《审计报告》是第一部全面反映我国审计事业、展示审计人风采的长篇小说。
     作品以恢宏的气势，迭宕起伏的情节，大胆地进入“审计”这鲜为人知的领域，成功地塑造了以方宏宇为代表的审计干部形象；生动细腻地展示了他们面对信念、事业、良心、家庭、感情、挫折与失误所表现出的激情和英雄主义的悲壮；向读者诠释了审计工作的内涵和意义。
     　　主人公方宏宇扛着离异的痛苦，就任审计署驻信州特派办副特派员，主持工作。方宏宇没有想到，他的到来给这个西部大省引发了一次强烈的“地震”，他更没有想到因使命所趋展开的一系列正常审计，把自己推到了极为尴尬的情法较量境地，也由此揭开了一个骇人的惊天大案。
     　　各大势威的信州高速集团，既是西部某省的一个标杆企业，又是一个存在严重违法违纪的“独立王国”，面对犯罪分子的恫吓利诱，栽赃陷害，偷梁换柱，面对亲情恩情友情，方宏宇临危不乱，在审计与反审计明争暗斗的智者较量中，向祖国和人民交上了一份满意的审计报告。
     有审计人在，国有资产秋毫无损！
     * price : 38.00元
     */

    private String code;
    private RatingBean rating;
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
    private List<TagsBean> tags;
    private List<?> translator;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

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

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<?> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 24
         * average : 7.6
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s9127731.jpg
         * large : https://img3.doubanio.com/lpic/s9127731.jpg
         * medium : https://img3.doubanio.com/mpic/s9127731.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class TagsBean {
        /**
         * count : 16
         * name : 审计
         * title : 审计
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
