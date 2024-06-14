package kr.co.jhta.app.dao;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class TimeCheck implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        Object obj = invocation.proceed();
        sw.stop();
        System.out.println("메서드 실행 시간 : " + (int) sw.getTotalTimeSeconds() + "초");
        return obj;

    }
}
