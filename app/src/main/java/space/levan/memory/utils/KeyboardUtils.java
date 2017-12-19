package space.levan.memory.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 佛祖保佑 不出BUG
 *
 * @author WangZhiYao
 * @date 2017/12/18
 */

public class KeyboardUtils {

    /**
     * Does the soft keyboard open
     *
     * @param context Context
     * @return
     */
    public static boolean isKeyboardOpen(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm != null && imm.isActive();
    }

    /**
     * Open the soft keyboard
     *
     * @param editText EditText
     * @param context  Context
     */
    public static void openKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * Close the soft keyboard
     *
     * @param editText EditText
     * @param context  Context
     */
    public static void closeKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
