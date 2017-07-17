package space.levan.memory.utils;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import space.levan.memory.App;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class RealmUtils
{
    public static void setActiveUser(SyncUser syncUser)
    {
        SyncConfiguration syncConfiguration = new SyncConfiguration
                .Builder(syncUser, App.AUTH_URL).build();
        Realm.setDefaultConfiguration(syncConfiguration);
    }

    public static void logoutActiveUser()
    {
        SyncUser.currentUser().logout();
    }
}
