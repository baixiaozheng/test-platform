package com.czn.testplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("toIndex")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
}
