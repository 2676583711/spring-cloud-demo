package cn.zhou.cruddemo.sercurity;

import cn.zhou.cruddemo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping(value = "/login")
//@Controller
public class LoginController {


    @GetMapping
    public Object login() {
        System.out.println("login");
        return "login.html";
    }

    @PostMapping
    public Object auth(User user) {
        System.out.println("auth");

        return null;
    }


    @GetMapping(value = "/fail")
    public Object fail(User user) {
        System.out.println("fail");

        return null;
    }
}
