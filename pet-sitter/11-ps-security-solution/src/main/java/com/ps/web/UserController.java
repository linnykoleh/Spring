package com.ps.web;

import com.ps.ents.User;
import com.ps.problem.NotFoundException;
import com.ps.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles requests to list all users.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.info("Populating model with list...");
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    /**
     * Handles requests to show detail about one user.
     */
    @RequestMapping(value = "/show/{id:[\\d]*}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) throws NotFoundException {
        User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException(User.class, id);
        }
        model.addAttribute("user", user);
        return "users/show";
    }

    /**
     * Handles requests to delete  a user
     */
    @RequestMapping(value = "/delete/{id:[\\d]*}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) throws NotFoundException {
        User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException(User.class, id);
        }
        userService.deleteById(id);
        model.addAttribute("confirmationMessage", "user.deleted");
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }
}
