package space.levan.memory.model.bean.splash;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class SplashBean {

    /**
     * id : 2EFoBSXgTCM
     * created_at : 2017-11-25T20:52:28-05:00
     * updated_at : 2017-11-26T23:56:55-05:00
     * width : 4480
     * height : 6720
     * color : #F66F6E
     * description : null
     * urls : {"raw":"https://images.unsplash.com/photo-1511661110100-994cc848c6ea","full":"https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=b1b6934b1e477ed15cc188b754ac8ae7","regular":"https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=0afd886b4e5f4e9b0014501f96e5d18e","small":"https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=78d8fb4dda24ee596889b0a0adb67c30","thumb":"https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=85da467d990cfe84c518d92d2af47004","custom":"https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&h=1920&fit=crop&s=216baf7d805e9b6d92d859db59ac5edd"}
     * categories : []
     * links : {"self":"api.unsplash.com/photos/2EFoBSXgTCM","html":"https://unsplash.com/photos/2EFoBSXgTCM","download":"https://unsplash.com/photos/2EFoBSXgTCM/download","download_location":"api.unsplash.com/photos/2EFoBSXgTCM/download"}
     * liked_by_user : false
     * likes : 58
     * user : {"id":"YR_kUAmnr18","updated_at":"2017-12-01T06:10:15-05:00","username":"trapnation","name":"Andre Benz","first_name":"Andre","last_name":"Benz","twitter_username":"AllTrapNation","portfolio_url":null,"bio":"Music & photos.","location":"Los Angeles, CA","links":{"self":"api.unsplash.com/users/trapnation","html":"https://unsplash.com/@trapnation","photos":"api.unsplash.com/users/trapnation/photos","likes":"api.unsplash.com/users/trapnation/likes","portfolio":"api.unsplash.com/users/trapnation/portfolio","following":"api.unsplash.com/users/trapnation/following","followers":"api.unsplash.com/users/trapnation/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1496596755198-2b84245c1bd3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=2e65c0e03c0e6d425a7d1d3e28aa9ac8","medium":"https://images.unsplash.com/profile-1496596755198-2b84245c1bd3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=b4c28f525e1f3b44c2072a819f78178e","large":"https://images.unsplash.com/profile-1496596755198-2b84245c1bd3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=cbe27b7eb022c73a05d51c9f6979664c"},"total_likes":0,"total_photos":105,"total_collections":1}
     * current_user_collections : []
     * slug : null
     * location : {"title":"New York, United States","name":"New York","city":"New York","country":"United States","position":{"latitude":40.7127753,"longitude":-74.0059728}}
     * exif : {"make":"Canon","model":"Canon EOS 5D Mark IV","exposure_time":"8","aperture":"5.6","focal_length":"50","iso":100}
     * views : 62223
     * downloads : 266
     */

    private String color;
    private String description;
    private UrlsBean urls;

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

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }

    public static class UrlsBean {
        /**
         * raw : https://images.unsplash.com/photo-1511661110100-994cc848c6ea
         * full : https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=b1b6934b1e477ed15cc188b754ac8ae7
         * regular : https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=0afd886b4e5f4e9b0014501f96e5d18e
         * small : https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=78d8fb4dda24ee596889b0a0adb67c30
         * thumb : https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=85da467d990cfe84c518d92d2af47004
         * custom : https://images.unsplash.com/photo-1511661110100-994cc848c6ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&h=1920&fit=crop&s=216baf7d805e9b6d92d859db59ac5edd
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
}
