package space.levan.memory.utils

import android.content.Context
import android.net.ConnectivityManager
import space.levan.memory.App

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

object NetUtils {

    val isNetConnected: Boolean
        get() {
            val connectivityManager = App.instance?.applicationContext?.
                    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null
        }
}