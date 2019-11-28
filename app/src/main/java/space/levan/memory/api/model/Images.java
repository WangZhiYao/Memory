package space.levan.memory.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * @author WangZhiYao
 * @date 2019/8/3
 */
public class Images implements Parcelable {

    @Nullable
    private String small;
    @Nullable
    private String medium;
    @Nullable
    private String large;

    @Nullable
    public String getSmall() {
        return small;
    }

    @Nullable
    public String getMedium() {
        return medium;
    }

    @Nullable
    public String getLarge() {
        return large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.medium);
        dest.writeString(this.large);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.small = in.readString();
        this.medium = in.readString();
        this.large = in.readString();
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
