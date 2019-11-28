package space.levan.memory.db.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE,
                parentColumns = {"isbn"}
        )},
        indices = {@Index(
                unique = true,
                value = {"isbn"}
        )}
)
public class BookCover {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    private Long id;
    @NonNull
    private String isbn = "";
    @Nullable
    private String small;
    @Nullable
    private String medium;
    @Nullable
    private String large;

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
    public String getSmall() {
        return small;
    }

    public void setSmall(@Nullable String small) {
        this.small = small;
    }

    @Nullable
    public String getMedium() {
        return medium;
    }

    public void setMedium(@Nullable String medium) {
        this.medium = medium;
    }

    @Nullable
    public String getLarge() {
        return large;
    }

    public void setLarge(@Nullable String large) {
        this.large = large;
    }
}
