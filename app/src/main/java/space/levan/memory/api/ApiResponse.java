package space.levan.memory.api;

/**
 * @author WangZhiYao
 * @date 2019/7/19
 */
public class ApiResponse<T> {

    private ApiStatus status;
    private int code;
    private T data;

    private ApiResponse(ApiStatus status) {
        this.status = status;
    }

    private ApiResponse(int code) {
        this.status = ApiStatus.ERROR;
        this.code = code;
    }

    private ApiResponse(T data) {
        this.status = ApiStatus.SUCCESS;
        this.data = data;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public static class Loading<T> extends ApiResponse<T> {
        public Loading() {
            super(ApiStatus.LOADING);
        }
    }

    public static class Success<T> extends ApiResponse<T> {
        public Success() {
            super(ApiStatus.SUCCESS);
        }

        public Success(T data) {
            super(data);
        }
    }

    public static class Error<T> extends ApiResponse<T> {
        public Error(int code) {
            super(code);
        }
    }
}
