package kr.co.jhta.app.springbootex11.control;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        // 현재 user의 id
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        // 현재 role
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role", role);

        return "home";
    }
}