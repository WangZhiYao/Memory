package space.levan.memory.db.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
@Entity(indices = {@Index(unique = true, value = {"isbn"})})
public class BookInfo {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    private Long id;
    @NonNull
    private String isbn = "";
    @NonNull
    private String title = "";
    @Nullable
    private List authors;
    @Nullable
    @ColumnInfo(name = "author_info")
    private String authorInfo;
    @Nullable
    private String cover;
    @Nullable
    private String summary;

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

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @Nullable
    public List getAuthors() {
        return authors;
    }

    public void setAuthors(@Nullable List authors) {
        this.authors = authors;
    }

    @Nullable
    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(@Nullable String authorInfo) {
        this.authorInfo = authorInfo;
    }

    @Nullable
    public String getCover() {
        return cover;
    }

    public void setCover(@Nullable String cover) {
        this.cover = cover;
    }

    @Nullable
    public String getSummary() {
        return summary;
    }

    public void setSummary(@Nullable String summary) {
        this.summary = summary;
    }
}
