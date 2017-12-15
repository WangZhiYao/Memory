package space.levan.memory.model.http.response;

import java.util.List;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/12/16
 */

public class UnSplashResponse<T> {

    private List<String> errors;
    private T result;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
