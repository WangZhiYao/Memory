package space.levan.memory.model;

/**
 * 获取图书信息listener
 *
 * Created by WangZhiYao on 2016/10/21.
 */

public interface OnGetBookInfoListener {

    void onGetInfoSuccess(String response);
    void onGetInfoFailure(String response);
}
