package cn.zhou.cruddemo.controller;

import cn.zhou.cruddemo.dao.UserDao;
import cn.zhou.cruddemo.pojo.User;
import cn.zhou.cruddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/crud")
//@RestController
@Controller
public class UserController {

    @Autowired
    UserService userService;

    //(consumes = "application/x-www-form-urlencoded")
    @PostMapping
    Object add(User user, Model model) {
        try {
            boolean b = userService.addUser(user);
            model.addAttribute("msg", "添加成功。");
        } catch (Exception e) {
            model.addAttribute("msg", "添加失败。");
        }
        return "index";
    }

    @DeleteMapping
    Object delete(User user, Model model) {
        try {
            boolean b = userService.addUser(user);
            model.addAttribute("msg", "删除成功。");
        } catch (Exception e) {
            model.addAttribute("msg", "删除失败。");
        }
        return "index";
    }

    @PutMapping
    Object update() {
        return null;
    }

    @GetMapping
    String query(User params, Model model) {
        if (params != null) {
            List<User> users = userService.findUser(params);
            model.addAttribute("users", users);
            return "index";
        }
        List<User> users = userService.getAllUsers();
        users.forEach(user -> System.out.println(user));
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/ajaxQuery")
    @ResponseBody
    Object ajaxQuery(User params) {
//        @RequestParam(required = false)
        List<User> users = userService.findUser(params);

        /*
        if (users != null) {
            return users;
        }
        users = userService.getAllUsers();
        users.forEach(user -> System.out.println("ajaxQuery:" + user));
        */
        return users;
    }
}
