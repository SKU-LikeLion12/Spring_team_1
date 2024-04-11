package likelion.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class taek {
    @GetMapping("/bye/{name}/{age}")
    public String byepath(@PathVariable String name,
                            @PathVariable String age,
                            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "bye";
    }
}
