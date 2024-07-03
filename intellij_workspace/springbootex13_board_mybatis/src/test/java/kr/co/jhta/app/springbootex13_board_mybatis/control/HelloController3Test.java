package kr.co.jhta.app.springbootex13_board_mybatis.control;

import kr.co.jhta.app.springbootex13_board_mybatis.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController3.class)
class HelloController3Test {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
    @MockBean // 이런 객체가 있다고 가정, @MockBean을 사용하여 HelloService를 모킹, 실제 HelloServiceImpl 대신에 모킹된 객체가 주입
    private HelloService helloService;

    @Test
//    @Disabled
    public void testHello() throws Exception {
        System.out.println(" helloService : " + helloService);
        when(helloService.sayHello())
                .thenReturn("안녕둘리");

        mockMvc.perform(get("/sayHi"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("안녕둘리"))
                .andDo(print());
    }



}