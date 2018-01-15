package space.levan.memory

import java.io.File

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2018/1/15
 */

interface Constants {
    companion object {

        val PATH_DATA = App.instance!!.cacheDir.absolutePath + File.separator + "data"
    }
}