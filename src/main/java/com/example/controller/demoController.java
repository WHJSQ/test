package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/9/5.
 */
//跨域
@CrossOrigin(origins = "http://blog.lqdev.cn", maxAge = 3600)
@RestController
public class demoController {
    @GetMapping("/demo1")
    public  String  index(){
        return "hello,CORS";
    }
}
