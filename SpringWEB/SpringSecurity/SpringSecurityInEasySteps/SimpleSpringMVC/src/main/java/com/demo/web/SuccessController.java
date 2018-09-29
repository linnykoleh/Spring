package com.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SuccessController {

    @RequestMapping(value = "/Success", method = RequestMethod.GET)
    public String renderSuccess(RedirectAttributes m) {
        return "success";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String renderHome(RedirectAttributes m, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "login";
        }
        return "home";
    }
}
