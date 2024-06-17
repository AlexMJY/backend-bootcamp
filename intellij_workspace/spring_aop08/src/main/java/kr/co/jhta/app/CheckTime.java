package kr.co.jhta.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
public class CheckTime {
    @Pointcut("execution(public void printEmail(..))")
    public void publicTarget() {}


    @Around("publicTarget()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        System.out.println("실행 중인 메서드 : " + name);
        StopWatch sw = new StopWatch();

        sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        System.out.println("실행 시간 : " + sw.getTotalTimeSeconds());
        return result;
    }
}