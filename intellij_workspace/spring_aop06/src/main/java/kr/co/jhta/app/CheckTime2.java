package kr.co.jhta.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class CheckTime2 {
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        // 실행중인 메서드의 이름 알아오기
        String methodName = pjp.getSignature().getName(); // method signature
        System.out.println("methodName : " + methodName);

        StopWatch sw = new StopWatch();
        sw.start();
        Object result = pjp.proceed();
        sw.stop();
        System.out.println("메서드 처리 시간 : " + sw.getTotalTimeSeconds() + "초");
        return result;
    }
}