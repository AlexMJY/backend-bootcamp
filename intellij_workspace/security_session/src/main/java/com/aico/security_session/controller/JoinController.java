package com.aico.security_session.controller;

import com.aico.security_session.dto.JoinDTO;
import com.aico.security_session.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        System.out.println("username : " + joinDTO.getUsername());
        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }
}
