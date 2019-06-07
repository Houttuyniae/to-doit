package io.todoit.common.expection;

/**
 * @className:BizException
 * @author:zhangd
 * @date:2019/2/4
 * @description:TODO
 */
public class BizException extends RuntimeException{
    public BizException(String message){
        super(message);
    }

//    public int getStatusCode(){
//        return Contants.RESP_STATUS_INTERNAL_ERROR;
//    }
}
