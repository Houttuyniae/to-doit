package io.todoit.common.response;


/**
 * @className:WebResultHandler
 * @author:zhangd
 * @date:2019/2/28
 * @description:TODO
 */
public class WebResultHandler {

    @SuppressWarnings("unchecked")
    public static <T> WebResult<T> ok(Class<T> clazz,Object object){
        if(!clazz.equals(object.getClass())){
            throw new NoClassDefFoundError("错误的参数类型");
        }
        WebResult<T> result = new WebResult<>();
        result.setCode(HttpStatusEnum.OK.getStatusCode());
        result.setData((T)object);
        return result;
    }

    public static <T> WebResult<T> ok(){
        WebResult<T> result = new WebResult<>();
        result.setCode(HttpStatusEnum.OK.getStatusCode());
        return result;
    }
    public static WebResult<Object> ok(Object object){
        WebResult<Object> result = new WebResult<>();
        result.setCode(HttpStatusEnum.OK.getStatusCode());
        result.setData(object);
        return result;
    }


    public static <T> WebResult<T> error(){
        WebResult<T> result = new WebResult<>();
        result.setCode(HttpStatusEnum.ERROR.getStatusCode());
        return result;
    }

    public static <T> WebResult<T> error(String message){
        return result(HttpStatusEnum.ERROR,message);
    }

    public static  <T> WebResult<T> result(HttpStatusEnum httpStatus,String message){
        WebResult<T> result = new WebResult<>();
        result.setCode(httpStatus.getStatusCode());
        result.setMsg(message);
        return result;
    }
}
