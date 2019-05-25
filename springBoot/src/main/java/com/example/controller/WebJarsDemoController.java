package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2018/9/5.
 */
@Controller
public class WebJarsDemoController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
