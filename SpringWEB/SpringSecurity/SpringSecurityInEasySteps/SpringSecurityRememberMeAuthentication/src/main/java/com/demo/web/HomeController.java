package com.demo.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView visitHome() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logOut(SecurityContextHolder sch, HttpServletRequest request) throws ServletException {
		ModelAndView mav = new ModelAndView("home");
		request.logout();
		//sch.getContext().setAuthentication(null);
		//sch.clearContext();
		return mav;
	}

	@RequestMapping(value = "/chief/updateProfile", method = RequestMethod.GET)
	public ModelAndView updatePage() {
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
		} else {
			return false;
		}
	}

}
