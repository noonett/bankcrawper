package com.tyh.bankcrawper.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class ClientLogAspect {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ClientLogAspect.class);

    @Pointcut("execution(* com.tyh.bankcrawper.client.*.*(..))")
    void pointcut(){
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info(String.format("在[%s], 运行了[%s]", now, target));
    }
}
