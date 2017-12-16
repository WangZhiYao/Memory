package space.levan.memory.model.bean.splash;

/**
 * Splash image entity
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class Splash {

    private String color;
    private String description;
    private Urls urls;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Splash{" +
                "color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", urls=" + urls.toString() +
                '}';
    }
}
