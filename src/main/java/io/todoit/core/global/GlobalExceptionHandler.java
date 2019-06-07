package io.todoit.core.global;

import io.todoit.common.response.HttpStatusEnum;
import io.todoit.common.response.WebResult;
import io.todoit.common.response.WebResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring全局异常捕获
 * @author:zhangd
 * @date:2019/4/2 20:22
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 特别说明： 可以配置指定的异常处理,这里处理所有
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public WebResult<String> errorHandler(HttpServletRequest request, Exception e) {
        log.error("request Exception:", e);
        return WebResultHandler.error("未知错误");
    }


    /**
     * 对通过@Vaild参数的注解，校验不通过的进行异常捕获
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult<String> validationErrorHandler(MethodArgumentNotValidException ex) {
        // 同样是获取BindingResult对象，然后获取其中的错误信息
        // 如果前面开启了fail_fast，事实上这里只会有一个信息
        //如果没有，则可能又多个
        List<String> errorInformation = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return WebResultHandler.result(HttpStatusEnum.BAD_REQUEST, errorInformation.get(0));
    }


}
