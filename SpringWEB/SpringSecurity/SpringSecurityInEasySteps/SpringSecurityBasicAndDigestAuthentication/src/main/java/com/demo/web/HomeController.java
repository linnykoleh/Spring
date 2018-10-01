package com.demo.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logOut(SecurityContextHolder sch,HttpServletRequest request) throws ServletException {
    	ModelAndView mav = new ModelAndView("home");
		request.logout();
    	//sch.getContext().setAuthentication(null);
		//sch.clearContext();
        return mav;
    }
}
