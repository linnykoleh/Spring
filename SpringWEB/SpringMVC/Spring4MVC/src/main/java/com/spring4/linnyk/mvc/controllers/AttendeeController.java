package com.spring4.linnyk.mvc.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring4.linnyk.mvc.model.Attendee;

@Controller
@SessionAttributes("attendee")
public class AttendeeController {

	@RequestMapping(value = "/attendee", method = RequestMethod.GET)
	public String displayAttendeePage(Model model) {
        final Attendee attendee = new Attendee();

        model.addAttribute("attendee", attendee);

		return "attendee";
	}

    @RequestMapping(value = "/attendee", method = RequestMethod.POST)
    public String processAttendee(@Valid Attendee attendee, BindingResult result, Model model) {
        System.out.println(attendee);

        if(result.hasErrors()){
        	return "attendee";
		}
        return "redirect:index.html";
    }

}
