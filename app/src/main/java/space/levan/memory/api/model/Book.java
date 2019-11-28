package space.levan.memory.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author WangZhiYao
 * @date 2019/8/3
 */
public class Book implements Parcelable {

    private int id;
    @SerializedName("isbn13")
    @Nullable
    private String isbn;
    @Nullable
    private String title;
    @Nullable
    private List<String> author;
    @SerializedName("author_intro")
    @Nullable
    private String authorIntro;
    @Nullable
    private String image;
    @Nullable
    private Images images;
    @Nullable
    private String summary;

    public int getId() {
        return id;
    }

    @Nullable
    public String getIsbn() {
        return isbn;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public List<String> getAuthor() {
        return author;
    }

    @Nullable
    public String getAuthorIntro() {
        return authorIntro;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    @Nullable
    public Images getImages() {
        return images;
    }

    @Nullable
    public String getSummary() {
        return summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.isbn);
        dest.writeString(this.title);
        dest.writeStringList(this.author);
        dest.writeString(this.authorIntro);
        dest.writeString(this.image);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.summary);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.id = in.readInt();
        this.isbn = in.readString();
        this.title = in.readString();
        this.author = in.createStringArrayList();
        this.authorIntro = in.readString();
        this.image = in.readString();
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.summary = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
