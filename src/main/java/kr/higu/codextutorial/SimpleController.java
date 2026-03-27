package kr.higu.codextutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping("/{name}")
    public String displayName(@PathVariable String name) {
        return "hi " + name;
    }

    @GetMapping("/age/{age}")
    public String displayAge(@PathVariable int age) {
        return "hi " + age;
    }
}
