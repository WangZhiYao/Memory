package space.levan.memory.model.prefs;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface SPrefsHelper {

    /**
     * 获取启动图片缓存路径
     *
     * @return 缓存路径
     */
    String getSplashPicPath();

    /**
     * 设置启动图片缓存路径
     *
     * @param picPath 缓存路径
     */
    void setSplashPicPath(String picPath);

    /**
     * 获取注册时用户填写的邮箱地址
     *
     * @return 邮箱地址
     */
    String getUserEmail();

    /**
     * 设置注册时用户填写的邮箱地址
     *
     * @param userEmail 邮箱地址
     */
    void setUserEmail(String userEmail);
}
