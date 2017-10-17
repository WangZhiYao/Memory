package space.levan.memory.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import space.levan.memory.app.App;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class NetUtils {

    public static boolean isNetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
