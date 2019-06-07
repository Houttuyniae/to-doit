package io.todoit.common.response;

/**
 * @className:WebResult
 * @author:zhangd
 * @date:2019/2/28
 * @description:公共的controller层返回对象
 */
public class WebResult<T> {

    private int code;

    private String msg;

    private T data;

    public int getStatusCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
