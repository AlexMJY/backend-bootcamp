package kr.co.jhta.app;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MessageImple implements Message, InitializingBean, DisposableBean {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printMsg() {
        System.out.println(name + "님 환영합니다.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 12번째 단계 때 실행되는 메서드
        System.out.println("12th : InitializingBean.afterPropertiesSet : 초기화 메서드 호출");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("2th DisposableBean's destroy(): 종료 메서드");
    }
}
