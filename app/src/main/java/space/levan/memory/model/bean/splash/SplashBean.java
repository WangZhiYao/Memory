package space.levan.memory.model.bean.splash;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class SplashBean {

    /**
     * id : 0Cg6xAsPQ4A
     * created_at : 2017-09-17T13:39:10-04:00
     * updated_at : 2017-09-29T09:14:46-04:00
     * width : 4469
     * height : 3992
     * color : #FAFAFA
     * slug : null
     * downloads : 552
     * likes : 17
     * views : 73973
     * liked_by_user : false
     * description : null
     * exif : {"make":"Sony","model":"ILCE-6000","exposure_time":"25","aperture":"8","focal_length":"16","iso":100}
     * location : {"title":"Kuopio, Finland","name":"Kuopio","city":"Kuopio","country":"Finland","position":{"latitude":62.89797,"longitude":27.6781725}}
     * current_user_collections : []
     * urls : {"raw":"https://images.unsplash.com/photo-1505669709397-2beccaa06812","full":"https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=8dbc242a00bad06169c4b81dd73b7a95","regular":"https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=3a4e20f158c51de1d0a12bfe310ebbc6","small":"https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=ed101262c558f24bb10c57da9b42be80","thumb":"https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=285c33cb0d58584394d94c2e3a99b314","custom":"https://images.unsplash.com/photo-1505669709397-2beccaa06812?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&h=1920&fit=crop&s=4f92dd0ae5d963db58bdf0e657073f39"}
     * categories : []
     * links : {"self":"https://api.unsplash.com/photos/0Cg6xAsPQ4A","html":"https://unsplash.com/photos/0Cg6xAsPQ4A","download":"https://unsplash.com/photos/0Cg6xAsPQ4A/download","download_location":"https://api.unsplash.com/photos/0Cg6xAsPQ4A/download"}
     * user : {"id":"vW9vS6nz-Fs","updated_at":"2017-10-16T23:50:42-04:00","username":"niiloi","name":"Niilo Isotalo","first_name":"Niilo","last_name":"Isotalo","twitter_username":null,"portfolio_url":null,"bio":"I'm a 19 year old nature photographer from Finland. Find me on Instagram @niiloi\r\n\r\nHave any questions? Send me an email at niilo.isotalo@gmail.com ","location":"Kuopio, Finland","total_likes":0,"total_photos":14,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1503081676097-7829a0b9daec?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=35c5f142f155f7b7a22208776e47e460","medium":"https://images.unsplash.com/profile-1503081676097-7829a0b9daec?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=003a9eabc273f3772d3a7f194d8e6a8d","large":"https://images.unsplash.com/profile-1503081676097-7829a0b9daec?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=3293641482ecb252c9845dbc42a9f04a"},"links":{"self":"https://api.unsplash.com/users/niiloi","html":"https://unsplash.com/@niiloi","photos":"https://api.unsplash.com/users/niiloi/photos","likes":"https://api.unsplash.com/users/niiloi/likes","portfolio":"https://api.unsplash.com/users/niiloi/portfolio","following":"https://api.unsplash.com/users/niiloi/following","followers":"https://api.unsplash.com/users/niiloi/followers"}}
     */

    private String color;
    private LocationBean location;
    private UrlsBean urls;
    private UserBean user;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
