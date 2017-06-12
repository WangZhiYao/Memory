package space.levan.memory.utils;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import space.levan.memory.BuildConfig;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class RealmUtils
{
    public static void setActiveUser(SyncUser syncUser)
    {
        SyncConfiguration syncConfiguration = new SyncConfiguration
                .Builder(syncUser, BuildConfig.REALM_SERVER_URL).build();
        Realm.getInstance(syncConfiguration);
    }

    public static void logoutActiveUser()
    {
        SyncUser.currentUser().logout();
    }

    public static Realm getInstance()
    {
        SyncConfiguration syncConfiguration = new SyncConfiguration
                .Builder(SyncUser.currentUser(), BuildConfig.REALM_SERVER_URL).build();
        return Realm.getInstance(syncConfiguration);
    }
}
