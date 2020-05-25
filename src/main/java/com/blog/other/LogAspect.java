package com.blog.other;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.blog.Controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获得类名.方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获得方法参数
        Object[] args = joinPoint.getArgs();

        Map m = new HashMap();

        m.put("url", url);
        m.put("ip", ip);
        m.put("classMethod", classMethod);
        m.put("args", args);

        logger.info("Request: {}", m);
    }


    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {

        logger.info("Result: {}", result);
    }

}

