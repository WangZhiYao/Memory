package space.levan.memory.bean;

/**
 * Book实体类
 *
 * Created by WangZhiYao on 2016/10/20.
 */

public class Book {

    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;

    public Long getId()
    {
        return id;
    }

    public String getBookName()
    {
        return bookName;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public String getBookPublisher()
    {
        return bookPublisher;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPublisher(String bookPublisher)
    {
        this.bookPublisher = bookPublisher;
    }

    public Book()
    {
        super();
    }

    public Book(Long id, String bookName, String bookAuthor, String bookPublisher)
    {
        super();
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
    }
}
