package com.aico.security_jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AdinController {

    @GetMapping("/admin")
    public String mainP() {
        return "admin controller";
    }
}
