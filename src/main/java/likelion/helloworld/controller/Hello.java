package likelion.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Hello {



    //asd

    @GetMapping("/hello_haewon")
    public String helloData(Model model) {
        model.addAttribute("name", "해원 !!!");
        model.addAttribute("age", "23세 !!!");
        model.addAttribute("job", "3학년 편입생 학생 !!!");
        model.addAttribute("major", "음악에서 개발까지 !! 컴퓨터공학과!!");

        return "hello";
    }
    @GetMapping("/hello/{name}/{age}/{major}")
    public String helloPath(@PathVariable String name,
                            @PathVariable String age,
                            @PathVariable String major,
                            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("major", major);
        return "hello";
    }

    @GetMapping("/hello-param")
    public String helloParam(@RequestParam("name") String name,
                             @RequestParam("age") String age,
                             @RequestParam("major") String major,
                             @RequestParam("job") String job,
                             Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("major", major);
        model.addAttribute("job", job);
        return "hello";
    }

    @GetMapping("/helloJin")
    public String helloJin(@RequestParam("name") String name,
                           @RequestParam("age") String year,
                           @RequestParam("major") String major,
                           @RequestParam("job") String job,
                           Model model){

        model.addAttribute("name", name);
        model.addAttribute("age", year);
        model.addAttribute("major", major);
        model.addAttribute("job", job);
        return "hello";
    }
}
