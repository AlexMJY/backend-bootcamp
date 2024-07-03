package kr.co.jhta.app.springbootex13_board_mybatis.test;

import org.mockito.Mockito;

import java.util.List;

public class Test02 {
    public static void main(String[] args) {

        List<String> list = Mockito.mock(List.class);

        Mockito.when(list.get(0)).thenReturn("포도");

        // call method
        list.get(0);

        Mockito.verify(list).get(0);
    }
}
