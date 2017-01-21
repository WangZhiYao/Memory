package space.levan.memory.bean;

/**
 * Created by WangZhiYao on 2017-01-21.
 */

public class BaseResponse {

    private int code;
    private String msg;
    private String error;

    public BaseResponse()
    {

    }

    public BaseResponse(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String error)
    {
        this.error = error;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
