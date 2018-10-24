package com.ps.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AllWebController {

	private final AllWebService allWebService;

	@Autowired
	public AllWebController(AllWebService allWebService) {
		this.allWebService = allWebService;
	}

	@RequestMapping("/all/{ownerId}")
	public String byOwner(Model model, @PathVariable("ownerId") Long ownerId) {
		final UserSkeleton owner = allWebService.findUserById(ownerId);
		if (owner != null) {
			owner.setPets(allWebService.findByOwnerId(ownerId));
		}
		log.debug("Added owner {} to model.", owner);
		model.addAttribute("owner", owner);
		return "all";
	}

	@RequestMapping("/pets/{type}")
	public String byOwner(Model model, @PathVariable("type") String type) {
		final List<PetSkeleton> pets = allWebService.findByType(type);
		model.addAttribute("pets", pets);
		model.addAttribute("type", type);

		log.debug("Added pets {} to model.", pets);
		log.debug("Added type {} to model.", type);
		return "pets";
	}

}
