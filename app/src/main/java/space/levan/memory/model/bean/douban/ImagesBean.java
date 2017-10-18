package space.levan.memory.model.bean.douban;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class ImagesBean {

    /**
     * small : https://img3.doubanio.com/spic/s5890592.jpg
     * large : https://img3.doubanio.com/lpic/s5890592.jpg
     * medium : https://img3.doubanio.com/mpic/s5890592.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
