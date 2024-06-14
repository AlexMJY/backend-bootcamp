package kr.co.jhta.app;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class CheckTime implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();  // 시간 측정기
        sw.start();  // 시간 측정 시작
        Object obj = invocation.proceed();  // 타겟의 메서드 실행
        sw.stop();  // 시간 측정 중지

        System.out.println("메서드 실행 시간 : " + (int) sw.getTotalTimeSeconds() + "초"); // sw.stop - sw.start

        return obj;
    }
}
