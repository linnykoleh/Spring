package com.demo.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView visitHome() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

    @RequestMapping(value = "/customlogout", method = RequestMethod.POST)
    public void logOut() {
    }


    @RequestMapping(value = "/chief/updateProfile", method = RequestMethod.GET)
    public ModelAndView updatePage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (rememberMeCheck()) {
            mav.setViewName("/login");
        } else {
            mav.setViewName("chiefUpdate");
        }
        return mav;

    }

    private boolean rememberMeCheck() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return (auth instanceof AnonymousAuthenticationToken || auth instanceof RememberMeAuthenticationToken);
        } else
            return false;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView("accessDenied");
        return mav;
    }
}