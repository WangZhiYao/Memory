package space.levan.memory.db.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
@Entity(
        foreignKeys = {@ForeignKey(
                entity = BookInfo.class,
                childColumns = {"isbn"},
                parentColumns = {"isbn"}
        )},
        indices = {@Index(value = {"isbn"})}
)
public class Note {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    private Long id;
    @NonNull
    private String isbn = "";
    @Nullable
    private String quotation;
    @ColumnInfo(name = "page_num")
    private int pageNum;
    @Nullable
    private String content;
    @ColumnInfo(name = "create_time")
    private long createTime;

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @NonNull
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NonNull String isbn) {
        this.isbn = isbn;
    }

    @Nullable
    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(@Nullable String quotation) {
        this.quotation = quotation;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
