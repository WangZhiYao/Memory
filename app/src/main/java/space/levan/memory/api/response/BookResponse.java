package space.levan.memory.api.response;

import androidx.annotation.Nullable;

import java.util.List;

import space.levan.memory.api.model.Book;

/**
 * @author WangZhiYao
 * @date 2019/7/1
 */
public class BookResponse extends BaseResponse {

    private int count;
    private int start;
    private int total;
    @Nullable
    private List<Book> books;

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    @Nullable
    public List<Book> getBooks() {
        return books;
    }
}
