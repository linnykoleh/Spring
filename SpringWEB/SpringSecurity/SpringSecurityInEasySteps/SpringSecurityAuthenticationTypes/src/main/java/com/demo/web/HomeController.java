package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
     
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView visitHome() {
    	ModelAndView mav = new ModelAndView("home");
        return mav;
    }
    
}