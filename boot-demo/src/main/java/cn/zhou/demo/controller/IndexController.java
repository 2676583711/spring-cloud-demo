package cn.zhou.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
/*
    @GetMapping(value = "/")
    public Object index() {
        return "static/index.html";
    }
    */

    @GetMapping(value = "/test")
    public Object index() {
        return "test";
    }
}
