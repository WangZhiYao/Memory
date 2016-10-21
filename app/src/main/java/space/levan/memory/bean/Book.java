package space.levan.memory.bean;

import java.util.List;

/**
 * 豆瓣Api从ISBN查询的图书信息实体类
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public class Book {

    /**
     * rating : {"max":10,"numRaters":0,"average":"0.0","min":0}
     * subtitle :
     * author : ["传智播客高教产品研发部"]
     * pubdate : 2015-1-1
     * tags : []
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s28355345.jpg
     * binding : 平装
     * translator : []
     * catalog : 第一章　Android基础入门
     1.1  Android简介
     1.1.1  通信技术
     1.1.2  Android起源
     1.1.3  Android体系结构
     1.1.4  Dalvik虚拟机
     1.2  Android开发环境搭建
     1.2.1  ADT Bundle
     开发工具集合
     1.2.2  Android调试桥(ADB)
     1.2.3  DDMS的使用
     1.3  开发第一个Android程序
     1.3.1  案伊卜——HelloWorld程序
     1.3.2  Android程序结构
     1.3.3  Android程序打包过程
     小结
     习题
     第二章　Android UI开发
     2.1  UI概述
     2.2  布局文件的创建
     2.3  布局的类型
     2.3.1  相对布局
     (RelativeLayout)
     2.3.2  线性布局
     (LinearLayout)
     2.3.3  表格布局
     (TableLayout)
     2.3.4  网格布局(GfidLayout)
     2.3.5  帧布局(FrameLayout)
     2.3.6  绝对布局
     (AbsoluteLayout)
     2.3.7  案例——用户注册
     2.4样式和主题
     2.4.1  样式和主题的使用
     2.4.2  案例——自定义样式和
     主题
     2.5  国际化
     2.6  程序调试
     2.6.1  JUnit单元测试
     2.6.2  LogCat的使用
     2.6.3  Toast的使用
     小结
     习题
     第三章　Activity
     3.1  Activity入门
     3.1.1  Activity简介
     3.1.2  Activity的创建
     3.1.3 Activity 生命周期
     3.1.4  案例——Activity的
     存活
     3.2  Activity的启动模式
     3.2.1  Android下的任务栈
     3.2.2  Activity的4种
     启动模式
     3.3  在Activity中使用Intent
     3.3.1  Intent介绍
     3.3.2  显式意图和隐式意图
     3.3.3  案例——打开
     系统照相机
     3.4  Activity中的数据传递
     3.4.1  数据传递方式
     3.4.2  案例——用户注册
     3.4.3  回传数据
     3.4.4  案例——装备选择
     小结
     习题
     第四章　数据存储
     第五章　SQLite数据库
     第六章　内容提供者
     第七章　广播接收者
     第八章　服务
     第九章　网络编程
     第十章　高级编程
     * pages : 320
     * images : {"small":"https://img3.doubanio.com/spic/s28355345.jpg","large":"https://img3.doubanio.com/lpic/s28355345.jpg","medium":"https://img3.doubanio.com/mpic/s28355345.jpg"}
     * alt : https://book.douban.com/subject/26679223/
     * id : 26679223
     * publisher : 中国铁道出版社
     * isbn10 : 7113196209
     * isbn13 : 9787113196202
     * title : Android 移动应用基础教程
     * url : https://api.douban.com/v2/book/26679223
     * alt_title :
     * author_intro :
     * summary : 由传智播客高教产品研发部编著的《Android移动应用基础教程》为Android入门书籍，本书站在初学者的角度，将每个讲解的知识都进行了深入分析，并使用生动形象的情景化举例，将复杂和难以理解的知识进行简单化。针对每个知识点，精心设计了相应的案例，力求每个案例都能贴合实际工作需求，真正做到把书本上的知识应用到实际开发中。
     本书附有配套的教学PPT、题库（2000道）、教学视频、源代码、教学补充案例、教学设计等资源。同时，为了帮助初学者及时地解决学习过程中遇到的问题，传智播客还专门提供了免费的在线答疑平台，并承诺在3小时内针对问题给予解答。
     本书可作为高等院校本、专科计算机相关专业程序设计类课程专用教材。
     * price : 39.00元
     */

    private String pubdate;
    private String image;
    private String pages;
    /**
     * small : https://img3.doubanio.com/spic/s28355345.jpg
     * large : https://img3.doubanio.com/lpic/s28355345.jpg
     * medium : https://img3.doubanio.com/mpic/s28355345.jpg
     */
    private String publisher;
    private String isbn13;
    private String title;
    private List<String> author;
    private List<?> tags;
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

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<?> translator) {
        this.translator = translator;
    }
}
