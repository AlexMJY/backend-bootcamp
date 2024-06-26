package kr.co.jhta.app.springbootex05.control;

import kr.co.jhta.app.springbootex05.dto.DeptDTO;
import kr.co.jhta.app.springbootex05.dto.SampleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/ex1")
    public void ex1(Model model) {
        log.info("============= ex1 =============");

        model.addAttribute("title", "타임리프");

    }

    @GetMapping("/ex2")
    public void ex2(Model model) {
        log.info("============= ex2 =============");
        // builder()를 사용해서 sno: 1 / first: scott / last: tiger / regTime: now()
//        SampleDTO dto =  SampleDTO.builder().sno(1L).first("scott").last("tiger").regTime(LocalDateTime.now()).build();

        // 위와 같은 코드 10개 만들어서 list에 담기
        List<SampleDTO> list = new ArrayList<>();
//        for (Long i = 1L; i < 10L; i++) {
//            SampleDTO dto =  SampleDTO.builder().sno(1L).first("scott-" + i).last("tiger-" + i).regTime(LocalDateTime.now()).build();
//            list.add(dto);
//        }

        List<SampleDTO> list2 = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {SampleDTO dto = SampleDTO.builder()
                                                    .sno(i)
                                                    .first("scott.."+i)
                                                    .last("tiger.."+i)
                                                    .regTime(LocalDateTime.now())
                                                    .build(); return dto;})
                                                    .collect(Collectors.toList());

//        model.addAttribute("list", list);
        model.addAttribute("list", list2);
        model.addAttribute("name", "MJY");
    }


    // ex3 ==> test()  ==> ex3.html
    // "홍길동", "이순신", "둘리"
    @GetMapping("/ex3")
    public void test(Model model) {
        List<String> list = Arrays.asList("홍길동", "이순신", "둘리");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            int n = (int) (Math.random() * 100) + 1;
//            list.add(n);
//        }

        List<Integer> list = new Random().ints(5, 1, 101).boxed().toList();

        model.addAttribute("list", list);
    }

    @GetMapping("/ex5")
    public String ex5(Model model) {
        model.addAttribute("msg1", "<b>Spring</b>");
        return "/home/ex5";
    }

    @GetMapping("/ex6")
    public void ex6(Model model) {
        SampleDTO dto1 = SampleDTO.builder()
                .sno(1L)
                .first("scott")
                .last("tiger")
                .regTime(LocalDateTime.now())
                .build();

        SampleDTO dto2 = SampleDTO.builder()
                .sno(2L)
                .first("james")
                .last("lion")
                .regTime(LocalDateTime.now())
                .build();

        ArrayList<SampleDTO> alist = new ArrayList<>();
        alist.add(dto1);
        alist.add(dto2);

        // object를 저장해서 전달
        model.addAttribute("dto1", dto1);
        model.addAttribute("dto2", dto2);

        model.addAttribute("alist", alist);
    }

    @GetMapping("/ex7")
    public void ex7(Model model) {
        String param1 = "hello";
        String param2 = "world";
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
    }

    @GetMapping("/ex8")
    public void ex8(Model model) {
        model.addAttribute("nullvalue", null);
        model.addAttribute("data", "타임리프");

    }

    @GetMapping("/ex9")
    public void ex9(Model model) {
        DeptDTO dto1 = new DeptDTO(10, "IT", "서울");
        DeptDTO dto2 = DeptDTO.builder().deptno(20).dname("AI").loc("부산").build();
        DeptDTO dto3 = DeptDTO.builder().deptno(30).dname("경영").loc("서울").build();
        DeptDTO dto4 = DeptDTO.builder().deptno(40).dname("회계").loc("서울").build();

        log.info("dto3 : " + dto3);
        dto3 = dto3.toBuilder().loc("하이퐁").build();
        log.info("dto3 : " + dto3);


        ArrayList<DeptDTO> dList = new ArrayList<>();
        dList.add(dto1);
        dList.add(dto2);
        dList.add(dto3);
        dList.add(dto4);


        model.addAttribute("dList", dList);
        model.addAttribute("dto4", dto4);
    }

}
