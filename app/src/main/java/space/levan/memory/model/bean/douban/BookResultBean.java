package space.levan.memory.model.bean.douban;

import java.util.List;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class BookResultBean {

    /**
     * count : 0
     * start : 0
     * total : 0
     * books : []
     */

    private int count;
    private int start;
    private int total;
    private List<BookItemBean> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BookItemBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookItemBean> books) {
        this.books = books;
    }
}
