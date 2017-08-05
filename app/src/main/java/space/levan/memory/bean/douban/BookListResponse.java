package space.levan.memory.bean.douban;

import java.util.List;

/**
 * Created by WangZhiYao on 2017/4/27.
 */

public class BookListResponse extends BaseResponse {
    private int count;
    private int start;
    private int total;
    protected List<BookInfoResponse> books;

    public BookListResponse() {
    }

    public BookListResponse(int code, String msg) {
        super(code, msg);
    }

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

    public List<BookInfoResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookInfoResponse> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookListResponse{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}