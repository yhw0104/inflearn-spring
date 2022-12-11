package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");   // hello.html 파일의 ${data}의 data는 attributeName이름이랑 동일
        return "hello"; // 스프링 부트는 기본적으로 return값을 문자를 반환하면 resources/templates...의 hello라는 이름의 html파일을 찾아서 리턴한다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("id") String name, Model model) { // @RequestParam -> id이라는 파라미터를 요청 받아오겠다. id라는 값에 변수가 들어가면 그 변수를 받아오겠다는 얘기
        model.addAttribute("name", name);   // addAttribute의 attributeName= "name" 이거는 thymeleaf에서 사용하는 변수 이름이고 ${name} 이 변수 이름 안에 요청받은 파라미터(id라는 이름의 파라미터)를 사용 name
        return "hello-templete";
    }

    @GetMapping("hello-string")
    @ResponseBody   // http프로토콜 body부에 응답하겠다는 어노테이션
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }   // view 이런거 없이 그냥 해당 문자만 return --> api     리턴값이 간단한 string이면 StringConverter가 작동

    @GetMapping("hello-api")
    @ResponseBody   // default로 json으로 반환 (요즘은 xml 잘 안씀)
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();      // 객체를 리턴하면 JsonConverter가 작동
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
