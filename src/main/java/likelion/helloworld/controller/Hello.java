package likelion.helloworld.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hello {

    @GetMapping("/hello_hae")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello_haewon")
    public String helloData(Model model) {
        model.addAttribute("nameKey", "해원 !!!");
        return "hello";
    }
    @GetMapping("/hello/{name}/{age}")
    public String helloPath(@PathVariable String name,
                            @PathVariable String age,
                            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello";
    }

    @GetMapping("/hello-param")
    public String helloParam(@RequestParam("이름") String name,
                             @RequestParam("나이") String age,
                             @RequestParam("학과") String major,
                             @RequestParam("직업") String profess, Model model) {
        model.addAttribute("이름", name);
        model.addAttribute("나이", age);
        model.addAttribute("학과", major);
        model.addAttribute("직업", profess);
        return "hello";
    }
}
