package space.levan.memory.model.bean.douban;

import java.util.List;

/**
 * Book result entity
 *
 * @author WangZhiYao
 * @date 2017/11/30
 */

public class BookResult {

    private int count;
    private int start;
    private int total;
    private List<Books> books;

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

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookResult{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books.get(0).toString() +
                '}';
    }
}
