package kr.higu.codextutorial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping("/{name}")
    public String displayName(@PathVariable String name) {
        return "hi " + name;
    }

    @GetMapping("/age/{age}")
    public String displayAge(@PathVariable int age) {
        if (age < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "age must be non-negative");
        }
        return "hi " + age;
    }
}
