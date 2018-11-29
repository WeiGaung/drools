package com.wenba.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    @RequestMapping(value = "/index")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        //mv.setViewName("thymeleaf");
        //mv.addObject("name","欢迎使用Thymeleaf!");
        return mv;
    }
}
