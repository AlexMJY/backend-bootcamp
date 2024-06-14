package kr.co.jhta.app;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class HijackBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("공통 관심사 업무 지정");
        System.out.println("메서드가 실행되기 전에 가로채기");
    }
}
