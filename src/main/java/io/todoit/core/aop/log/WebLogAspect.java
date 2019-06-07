package io.todoit.core.aop.log;

import io.todoit.common.response.WebResult;
import io.todoit.common.response.WebResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @className:WebLogAspect
 * @author:zhangd
 * @date:2019/2/27
 * @description:TODO
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(* io.todoit.*.controller.*.*(..))")
    public void logPointcut(){

    }

    @Around("logPointcut() && @annotation(io.todoit.common.annotation.WebLog)")
    public WebResult<Object> aroundWebLog(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Object proceed = null;
        WebResult<Object> webResult = null;
        try {
            proceed = point.proceed(args);
            webResult = WebResultHandler.ok(proceed);
        }catch (Exception e){
            log.error("出現未知錯誤", e);
            webResult = WebResultHandler.error("出现未知错误，请联系管理员");
        }
        return webResult;
    }
}
