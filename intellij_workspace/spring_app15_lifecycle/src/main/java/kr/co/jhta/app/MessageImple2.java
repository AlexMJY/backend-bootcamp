package kr.co.jhta.app;

public class MessageImple2 implements Message {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    // 커스텀 초기화 메서드
    public void init() {
        System.out.println("커스텀 초기화 메서드 호출 중");
    }

    // 커스텀 종료 메서드
    public void destroy() {
        System.out.println("커스텀 종료 메서드 호출 중");
    }

    @Override
    public void printMsg() {
        System.out.println(name + "님 환영합니다.");
    }
}
