package space.levan.memory.utils.ocr;

/**
 * @author WangZhiYao
 * @date 2019/6/13
 */
public interface OnOCRResultListener {

    void onOCRSuccess(String path, String result);

    void onOCRFailure(String msg);
}
