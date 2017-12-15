package space.levan.memory.model.http.response;

/**
 * BaseResponse from DouBan Api
 *
 * @author WangZhiYao
 * @date 2017/12/16
 */

public class DouBanResponse<T> {

    /**
     * msg : uri_not_found
     * code : 1001
     * request : GET /v2/photo/132
     */

    private String msg;
    private int code;
    private String request;
    private T results;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
