package kr.co.jhta.app;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.time.LocalTime;

public class NowTime implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LocalTime now = LocalTime.now();
        System.out.println(now);

    }
}
