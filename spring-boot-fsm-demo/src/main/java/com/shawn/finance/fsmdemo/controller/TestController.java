package com.shawn.finance.fsmdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shawn on 15/12/24.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/")
    public String test(){
        System.out.println("Shit web");
        return "Shit web";
    }
}
