package space.levan.memory.utils;

import android.content.Context;
import android.content.Intent;

import space.levan.memory.App;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017/4/27.
 */

public class UIUtils {

    public static Context getContext() {
        return App.getApplication();
    }

    /**
     * 页面跳转
     *
     * @param intent
     */
    public static void startActivity(Intent intent) {
        // 如果不在activity里去打开activity  需要指定任务栈  需要设置标签
        if (BaseActivity.activity == null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            BaseActivity.activity.startActivity(intent);
        }
    }
}
