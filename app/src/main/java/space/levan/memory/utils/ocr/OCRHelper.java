package space.levan.memory.utils.ocr;

import android.content.Context;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.WordSimple;
import com.google.gson.Gson;

import java.io.File;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import space.levan.memory.log.Logger;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class OCRHelper {

    private static final String TAG = "OCRHelper";

    public static void init(Context context, OnOCRInitListener onOCRInitListener) {
        Single.create((SingleOnSubscribe<AccessToken>) emitter ->
                OCR.getInstance(context).initAccessToken(new OnResultListener<AccessToken>() {
                    @Override
                    public void onResult(AccessToken accessToken) {
                        emitter.onSuccess(accessToken);
                    }

                    @Override
                    public void onError(OCRError ocrError) {
                        emitter.onError(ocrError);
                    }
                }, context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AccessToken>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(AccessToken accessToken) {
                        if (onOCRInitListener != null) {
                            Logger.d(TAG, "OCR 初始化成功");
                            onOCRInitListener.onOCRInitSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (onOCRInitListener != null) {
                            Logger.e(TAG, t);
                            onOCRInitListener.onOCRInitFailure("OCR 初始化失败，错误码：" + ((OCRError) t).getErrorCode());
                        }
                    }
                });
    }

    public static void textRecognizeFromFile(Context context, String filePath, OnOCRResultListener onOCRResultListener) {
        GeneralBasicParams param = new GeneralBasicParams();
        param.setDetectDirection(true);
        param.setImageFile(new File(filePath));

        OCR.getInstance(context).recognizeGeneralBasic(param, new OnResultListener<GeneralResult>() {
            @Override
            public void onResult(GeneralResult generalResult) {
                Logger.d(TAG, "OCR 识别成功：" + new Gson().toJson(generalResult));

                StringBuilder result = new StringBuilder();
                if (generalResult != null && generalResult.getWordList() != null) {
                    for (WordSimple ws : generalResult.getWordList()) {
                        result.append(ws.getWords())
                                .append("\n");
                    }

                    if (result.length() > 0) {
                        result.deleteCharAt(result.lastIndexOf("\n"));
                    }

                    if (onOCRResultListener != null) {
                        onOCRResultListener.onOCRSuccess(filePath, result.toString());
                    }
                }
            }

            @Override
            public void onError(OCRError ocrError) {
                if (onOCRResultListener != null) {
                    Logger.e(TAG, ocrError);
                    onOCRResultListener.onOCRFailure("OCR 识别失败，错误码：" + ocrError.getErrorCode());
                }
            }
        });
    }

    public static void release(Context context) {
        OCR.getInstance(context).release();
    }
}
