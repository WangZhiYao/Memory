package space.levan.memory.model.db;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class RealmHelperImpl implements RealmHelper {

    public static final String DB_NAME = "Memory.realm";

    private Realm mRealm;

    @Inject
    public RealmHelperImpl() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }
}
