package cn.zhou.demo.controller;


import cn.zhou.demo.pojo.User;
import cn.zhou.demo.service.UserService;
import cn.zhou.demo.utils.PasswordCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static cn.zhou.demo.utils.MyUtil.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public Object login(User user, Model model) {
        if (isNull(user) || isNull(user.getUsername()) | isNull(user.getPassword())) {
            model.addAttribute("msg", "用户名或密码不能为空...");
            return "login";
        }
        try {
            User user1 = userService.findUserByUsername(user.getUsername());
            System.out.println("user1：" + user1);
            if (isNull(user1) | isNull(user1.getUsername())) {
                model.addAttribute("msg", "登录失败，该用户名不存在或未注册...");
                return "login";
            }
            if (user1.getPassword().equals(user.getPassword())) {
                model.addAttribute("msg", "登录成功...");
                model.addAttribute("username", user.getUsername());
                model.addAttribute("password", user.getPassword());
                return "success";
            } else {
                model.addAttribute("msg", "密码错误，登录失败...");
                return "login";
            }
        } catch (Exception e) {
            System.out.println("登录错误：" + e.getCause());
            model.addAttribute("msg", "网络异常，登录失败...");
        }
        return "login";
    }

    @GetMapping(value = "/goToRegister")
    public Object goToRegister() {
        return "register";
    }

    @PostMapping(value = "/register")
    public Object register(User user, Model model) {
        if (isNull(user) || (isNull(user.getUsername()) | isNull(user.getPassword()))) {
            model.addAttribute("msg", "用户名或密码不能为空...");
            return "register";
        }
        User user1 = userService.findUserByUsername(user.getUsername());
        if (isNotNull(user1)) {
            model.addAttribute("msg", "该用户名:" + user.getUsername() + ",已经被注册...");
            return "register";
        }
        String s = checkPassword(user.getPassword());
        if (s != null) {
            model.addAttribute("msg", s);
            return "register";
        }


        try {
            userService.addUser(user);
            model.addAttribute("msg", "注册成功,可以去登陆了...");
        } catch (Exception e) {
            model.addAttribute("msg", "网络异常，注册失败...");
        }
        return "register";
    }

    public String checkPassword(String password) {
        if (password.length() <= 6) {
            return "密码最少6位...";
        }

        if (!PasswordCheck.isContainLetter(password)) {
//            model.addAttribute("msg", "密码需要包含字母...");
            return "密码需要包含字母...";
        }
        if (!PasswordCheck.isContainDigit(password)) {
//            model.addAttribute("msg", "密码需要包含数字...");
            return "密码需要包含数字...";
        }
        return null;
    }
}
