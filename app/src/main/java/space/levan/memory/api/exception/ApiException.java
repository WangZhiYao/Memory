package space.levan.memory.api.exception;

/**
 * @author WangZhiYao
 * @date 2019/7/11
 */
public class ApiException extends Exception {

    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_SERVER_ERROR = 500;

    public static final int UNKNOWN_EXCEPTION = 10000;
    public static final int JSON_EXCEPTION = 10001;

    private int code;

    public ApiException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
