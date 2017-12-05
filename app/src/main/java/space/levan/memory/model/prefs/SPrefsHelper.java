package space.levan.memory.model.prefs;

/**
 * SharedPreferences interface class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface SPrefsHelper {

    /**
     * fetch the splash image cached path
     *
     * @return
     */
    String getSplashPicPath();

    /**
     * save the splash image cached path
     *
     * @param picPath cached path
     */
    void setSplashPicPath(String picPath);

    /**
     * fetch the email address that user use to sign in
     *
     * @return 邮箱地址
     */
    String getUserEmail();

    /**
     * save the email address that user use to sign up
     *
     * @param userEmail 邮箱地址
     */
    void setUserEmail(String userEmail);
}
