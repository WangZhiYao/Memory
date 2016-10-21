package space.levan.memory.bean;

import java.util.List;

/**
 * 豆瓣Api从ISBN查询的图书信息实体类
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public class Book {


    /**
     * rating : {"max":10,"numRaters":9,"average":"0.0","min":0}
     * subtitle :
     * author : ["传智播客高教产品研究部编著"]
     * pubdate : 2014-5-1
     * tags : [{"count":12,"name":"java","title":"java"},{"count":5,"name":"java基础入门","title":"java基础入门"},{"count":4,"name":"IT","title":"IT"},{"count":3,"name":"入门不错","title":"入门不错"},{"count":2,"name":"Java","title":"Java"},{"count":1,"name":"地","title":"地"},{"count":1,"name":"想看","title":"想看"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s27323402.jpg
     * binding : 平装
     * translator : []
     * catalog : 第1章Java开发入门
     1．1Java概述
     1．1．1什么是Java
     1．1．2Java语言的特点
     1．2JDK的使用
     1．2．1什么是JDK
     1．2．2安装JDK
     1．2．3JDK目录介绍
     1．3第一个Java程序
     1．4系统环境变量
     1．4．1path环境变量
     1．4．2classpath环境变量
     1．5Java的运行机制
     1．6本章小结
     1．7习题第1章Java开发入门
     1．1Java概述
     1．1．1什么是Java
     1．1．2Java语言的特点
     1．2JDK的使用
     1．2．1什么是JDK
     1．2．2安装JDK
     1．2．3JDK目录介绍
     1．3第一个Java程序
     1．4系统环境变量
     1．4．1path环境变量
     1．4．2classpath环境变量
     1．5Java的运行机制
     1．6本章小结
     1．7习题
     第2章Java编程基础
     2．1Java的基本语法
     2．1．1Java代码的基本格式
     2．1．2Java中的注释
     2．1．3Java中的标识符
     2．1．4Java中的关键字
     2．1．5Java中的常量
     2．2Java中的变量
     2．2．1变量的定义
     2．2．2变量的数据类型
     2．2．3变量的类型转换
     2．2．4变量的作用域
     2．3Java中的运算符
     2．3．1算术运算符
     2．3．2赋值运算符
     2．3．3比较运算符
     2．3．4逻辑运算符
     2．3．5位运算符
     2．3．6运算符的优先级
     2．4选择结构语句
     2．4．1if条件语句
     2．4．2switch条件语句
     2．5循环结构语句
     2．5．1While循环语句
     2．5．2dowhile循环语句
     2．5．3for循环语句
     2．5．4循环嵌套
     2。5．5跳转语句(break、continue)
     2．6方法
     2．6．1什么是方法
     2．6．2方法的重载
     2．6．3方法的递归
     2．7数组
     2．7．1数组的定义
     2．7．2数组的常见操作
     2．7．3多维数组
     2．8本章小结
     2．9习题
     第3章面向对象(上)
     3．1面向对象的概念
     3．2类与对象
     3．2．1类的定义
     3．2．2对象的创建与使用
     3．2．3类的设计
     3．2．4类的封装
     3．3构造方法
     3．3．1构造方法的定义
     3．3．2构造方法的重载
     ..
     第4章面向对象（下）
     第5章多线程
     第6章javaAPI
     第7章集合类
     第8章IO（输入输出）
     第9章GUI(国产用户界面)
     第10章网络编程
     第11章Eclipse开发工具
     * pages : 426
     * images : {"small":"https://img3.doubanio.com/spic/s27323402.jpg","large":"https://img3.doubanio.com/lpic/s27323402.jpg","medium":"https://img3.doubanio.com/mpic/s27323402.jpg"}
     * alt : https://book.douban.com/subject/25926153/
     * id : 25926153
     * publisher : 清华大学出版社
     * isbn10 : 7302359385
     * isbn13 : 9787302359388
     * title : Java基础入门
     * url : https://api.douban.com/v2/book/25926153
     * alt_title :
     * author_intro :
     * summary : 由传智播客高教产品研发部编著的《Java基础入门》从初学者的角度详细讲解了Java开发中重点用到的多种技术。全书共11章，包括Java开发环境的搭建及其运行机制、基本语法、面向对象的思想，采用典型翔实的例子、通俗易懂的语言阐述面向对象中的抽象概念。在多线程、常用API、集合、IO、GUI、网络编程章节中，通过剖析案例、分析代码结构含义、解决常见问题等方式，帮助初学者培养良好的编程习惯。最后，讲解了Eclipse开发工具，帮助初学者熟悉开发工具的使用。
     《Java基础入门》附有配套视频、源代码、测试题、教学PPT、教学实施案例、教学设计大纲等资源，并提供在线答疑平台。
     本书既可作为高等院校本、专科计算机相关专业的程序设计课程教材，也可作为Java技术基础的培训教材，是一本适合广大计算机编程初学者的入门级教材。
     * price :
     */

    private String pubdate;
    private String image;
    private String pages;
    /**
     * small : https://img3.doubanio.com/spic/s27323402.jpg
     * large : https://img3.doubanio.com/lpic/s27323402.jpg
     * medium : https://img3.doubanio.com/mpic/s27323402.jpg
     */

    private ImagesBean images;
    private String publisher;
    private String isbn13;
    private String title;
    private String author_intro;
    private String summary;
    private List<String> author;
    /**
     * count : 12
     * name : java
     * title : java
     */

    private List<TagsBean> tags;
    private List<?> translator;

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

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public static class ImagesBean {
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
