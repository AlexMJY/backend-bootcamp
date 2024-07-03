package kr.co.jhta.app.springbootex13_board_mybatis.test;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class Test01 {
    public static void main(String[] args) {
        
        // Mock : 테스트 시나리오에서 특정 객체의 행위를 흉내내는 객체

        // 프레임워크를 이용해서 Mock 객체를 쉽게 만들 수 있음
        // Mock framework, Mockito, EasyMock, JMockit ...


        List<String> list = mock(List.class);

        when(list.size()).thenReturn(3);

        System.out.println(list.size());

        // 객체를 생성하고
//        List<String> list = new ArrayList<>();
//        list.add("포도");
//        list.add("딸기");
//        list.add("두리안");

        // 객체가 동작하는지 검사

        // 리스트에 3개의 객체가 존재하는지
//        System.out.println(list.size() == 3);

        // 첫번째 데이터의 값이 포도인지 검사
//        System.out.println(list.get(0).equals("포도"));
    }
}
