package space.levan.memory.model.bean.splash;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/18
 */

public class UrlsBean {

    /**
     * raw : https://images.unsplash.com/photo-1505669709397-2beccaa06812
     * full : https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=8dbc242a00bad06169c4b81dd73b7a95
     * regular : https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=3a4e20f158c51de1d0a12bfe310ebbc6
     * small : https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=ed101262c558f24bb10c57da9b42be80
     * thumb : https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=285c33cb0d58584394d94c2e3a99b314
     * custom : https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&h=1920&fit=crop&s=4f92dd0ae5d963db58bdf0e657073f39
     */

    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;
    private String custom;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
