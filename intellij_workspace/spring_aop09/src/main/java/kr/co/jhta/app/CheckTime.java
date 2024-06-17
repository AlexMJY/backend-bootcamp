package kr.co.jhta.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
public class CheckTime {

    @Pointcut("execution(public void reload(..))")
    public void publicTarget() {}

    @Around("publicTarget()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName : " + methodName);

        StopWatch sw = new StopWatch();
        sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        System.out.println("method execute time : " + sw.getTotalTimeSeconds());

        return result;
    }
}
