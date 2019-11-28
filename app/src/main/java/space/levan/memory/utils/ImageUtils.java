package space.levan.memory.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import androidx.annotation.NonNull;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import space.levan.memory.R;
import space.levan.memory.utils.glide.GlideEngine;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class ImageUtils {

    public static void openImageSelector(@NonNull Activity activity, int requestCode) {
        Matisse.from(activity)
                .choose(MimeType.ofImage(), false)
                .capture(true)
                .captureStrategy(new CaptureStrategy(false, "space.levan.memory.fileprovider"))
                .theme(R.style.AppTheme_Matisse)
                .countable(false)
                .maxSelectable(1)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85F)
                .imageEngine(new GlideEngine())
                .forResult(requestCode);
    }
}
