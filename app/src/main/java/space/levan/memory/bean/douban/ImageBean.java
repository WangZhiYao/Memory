package space.levan.memory.bean.douban;

import java.io.Serializable;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class ImageBean implements Serializable {

    private String small;
    private String medium;
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
