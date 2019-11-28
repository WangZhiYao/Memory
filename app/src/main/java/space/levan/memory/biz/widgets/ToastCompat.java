package space.levan.memory.biz.widgets;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;

import space.levan.memory.R;
import space.levan.memory.log.Logger;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class ToastCompat {

    private static final String TAG = "ToastCompat";

    private Toast mToast;

    private ToastCompat(Context context, CharSequence text, int duration) {
        View toastView = LayoutInflater.from(context).inflate(R.layout.layout_toast_compat, null);
        TextView textView = toastView.findViewById(R.id.tv_toast_msg);
        textView.setText(text);
        mToast = new Toast(context);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
            hook(mToast);
        }
        mToast.setDuration(duration);
        mToast.setView(toastView);
    }

    public static ToastCompat makeText(Context context, CharSequence text, int duration) {
        return new ToastCompat(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    /**
     * Handle BadTokenException on Android 7.1.1
     *
     * @param toast
     */
    private void hook(Toast toast) {
        try {
            Field fieldTN = Toast.class.getDeclaredField("mTN");
            fieldTN.setAccessible(true);
            Field fieldTNHandler = fieldTN.getType().getDeclaredField("mHandler");
            fieldTNHandler.setAccessible(true);
            Object tn = fieldTN.get(toast);
            Handler preHandler = (Handler) fieldTNHandler.get(tn);
            fieldTNHandler.set(tn, new SafeHandler(preHandler));
        } catch (Exception ex) {
            Logger.e(TAG, ex);
        }
    }

    private static class SafeHandler extends Handler {

        private Handler impl;

        SafeHandler(Handler impl) {
            this.impl = impl;
        }

        @Override
        public void dispatchMessage(@NonNull Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception ex) {
                Logger.e(TAG, ex);
            }
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            //需要委托给原Handler执行
            impl.handleMessage(msg);
        }
    }
}
