package io.todoit.common.response;

/**
 * @className:RespstatusCode
 * @author:zhangd
 * @date:2019/2/28
 * @description:TODO
 */
public enum HttpStatusEnum {
    /**
     * 程序正常运行
     */
    OK(200),
    /**
     * 程序出现未知错误
     */
    ERROR(500),

    /**
     * 錯誤的請求,参数错误
     */
    BAD_REQUEST(400),

    /**
     * token错误
     */
    TOKEN_ERROR(401);


    private int statusCode;
    private String message;

    HttpStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    HttpStatusEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
