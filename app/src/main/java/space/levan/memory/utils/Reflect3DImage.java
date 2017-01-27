package space.levan.memory.utils;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;

/**
 * Created by WangZhiYao on 2017-01-24.
 */

public class Reflect3DImage {

    public static Bitmap skewImage(Bitmap paramBitmap)
    {
        Camera localCamera = new Camera();
        localCamera.save();
        Matrix localMatrix = new Matrix();
        localCamera.rotateY(-13f);
        localCamera.getMatrix(localMatrix);
        localCamera.restore();
        localMatrix.postScale(1, 1.06f);
        localMatrix.preTranslate(-paramBitmap.getWidth() >> 1, -paramBitmap.getHeight() >> 1);
        Bitmap localBitmap2 = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
        Bitmap localBitmap3 = Bitmap.createBitmap(localBitmap2.getWidth(), localBitmap2.getHeight()+40, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap3);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        localPaint.setFilterBitmap(true);
        localCanvas.drawBitmap(localBitmap2, 0.0F, 0.0F, localPaint);
        localBitmap2.recycle();
        return localBitmap3;
    }
}
