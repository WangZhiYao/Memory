package space.levan.memory.utils.ocr;

/**
 * @author WangZhiYao
 * @date 2019/6/13
 */
public interface OnOCRInitListener {

    void onOCRInitSuccess();

    void onOCRInitFailure(String msg);
}
