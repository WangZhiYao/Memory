package space.levan.memory.model;

import android.graphics.Bitmap;

/**
 * 获取图书信息listener
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public interface OnGetBookInfoListener {

    void onGetInfoSuccess(String response);
    void onGetInfoFailure(String response);
    void onGetImgSuccess(Bitmap bitmap);
    void onGetImgFailure(String response);
}
