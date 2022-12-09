package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");   // hello.html 파일의 ${data}의 data는 attributeName이름이랑 동일
        return "hello"; // 스프링 부트는 기본적으로 return값을 문자를 반환하면 resources/templates...의 hello라는 이름의 html파일을 찾아서 리턴한다.
    }
}
