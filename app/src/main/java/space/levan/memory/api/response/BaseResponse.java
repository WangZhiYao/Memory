package space.levan.memory.api.response;

import androidx.annotation.Nullable;

/**
 * @author WangZhiYao
 * @date 2019/7/1
 */
public class BaseResponse {

    @Nullable
    private String msg;
    private int code;

    @Nullable
    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
