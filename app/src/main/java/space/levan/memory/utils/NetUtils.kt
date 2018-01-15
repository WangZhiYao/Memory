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

    fun isNetConnected(): Boolean {
        val cm = App.instance!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}