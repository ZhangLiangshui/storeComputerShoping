package com.cy.store.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

@Component//将当前类的对象创建使用交给spring容器
@Aspect//将当前类标记为切面类
public class TimerAspect {
    //使用环绕通知
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //记录当前时间
        long start = System.currentTimeMillis();
         Object result = pjp.proceed();//调用目标方法
        long end =System.currentTimeMillis();
        System.out.println("耗时"+(end-start));
        return result;
    }
}
