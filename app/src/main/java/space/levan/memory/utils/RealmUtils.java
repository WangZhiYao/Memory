package space.levan.memory.utils;

import android.content.Context;

import io.realm.Realm;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/14
 */

public class RealmUtils {

    public static void initRealm(Context context) {
        Realm.init(context);
    }
}
