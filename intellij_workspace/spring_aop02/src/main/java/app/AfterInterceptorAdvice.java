package app;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterInterceptorAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("메서드가 호출된 후에 실행되는 메서드");
    }
}
