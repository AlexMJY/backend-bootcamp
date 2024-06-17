package kr.co.jhta.app.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class CheckTime {
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        System.out.println("실행중인 메서드 : " + name);

        StopWatch sw = new StopWatch();
        sw.start();
        Object result = joinPoint.proceed();
        sw.stop();
        System.out.println("실행 시간 : " + sw.getTotalTimeSeconds() + "초" );
        
        return result;
    }
}
