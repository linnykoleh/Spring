package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dao.UserDao;
import com.demo.to.UserTo;

@Controller
public class HomeController {

	private final UserDao ud;

    @Autowired
    public HomeController(UserDao ud) {
        this.ud = ud;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView visitHome() {
        return new ModelAndView("home");
	}

	@RequestMapping(value = "/signupRequest", method = RequestMethod.GET)
	public String registration(Model model) {
        model.addAttribute("userTo", new UserTo());
        return "signup";
    }
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView singUp(@ModelAttribute("userTo") UserTo user,RedirectAttributes attr) {
		ModelAndView mav = new ModelAndView();
		String s = ud.save(user);
		if (s.equalsIgnoreCase("Successful")) {
			mav.setViewName("login");
			return mav;
		} else {
			attr.addFlashAttribute("user", user);
			attr.addFlashAttribute("error", s);
			mav.addObject("error", s);
			mav.setViewName("signup");
			return mav;
		}
	}
}