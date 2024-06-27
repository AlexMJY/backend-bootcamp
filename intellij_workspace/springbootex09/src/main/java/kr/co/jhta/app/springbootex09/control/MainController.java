package kr.co.jhta.app.springbootex09.control;

import kr.co.jhta.app.springbootex09.entity.Member;
import kr.co.jhta.app.springbootex09.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;

    @GetMapping("/info")
    public String info(Model model) {
        List<Member> list = memberRepository.findAll();

        model.addAttribute("msg", "hello");
        model.addAttribute("list", list);
        return "info";
    }

    @GetMapping("/add")
    public String add() {
        return "addForm";
    }

    @PostMapping("/add")
    public String addOk(@ModelAttribute Member member) {
        memberRepository.save(member);

        return "redirect:/member/info";
    }

    @GetMapping("/delete/{no}")
    public String delete(@PathVariable Long no) {
        memberRepository.deleteById(no);
        return "redirect:/member/info";
    }

    @GetMapping("/update/{no}")
    public String updateForm(Model model, @PathVariable Long no) {
        Member member = memberRepository.findById(no).get();
        System.out.println("member : " + member);
        model.addAttribute("member", member);
        return "updateForm";
    }

    @PostMapping("/update")
    public String updateOk(@ModelAttribute Member member) {
        memberRepository.save(member);
        return "redirect:/member/info";
    }

}


