package kr.co.jhta.app.springbootex13_board_mybatis.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello 둘리";
    }
}
