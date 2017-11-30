package space.levan.memory.model.bean.douban;

import io.realm.RealmObject;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class ImagesBean extends RealmObject {

    private String small;
    private String large;
    private String medium;

    public void setSmall(String small) {
        this.small = small;
    }

    public String getSmall() {
        return small;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getLarge() {
        return large;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return medium;
    }
}
