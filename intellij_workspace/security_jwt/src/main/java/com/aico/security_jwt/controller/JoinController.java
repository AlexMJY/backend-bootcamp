package com.aico.security_jwt.controller;

import com.aico.security_jwt.dto.JoinDTO;
import com.aico.security_jwt.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }


    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {
        System.out.println("username : " + joinDTO.getUsername());
        joinService.joinProcess(joinDTO);
        return "ok";
    }
}
