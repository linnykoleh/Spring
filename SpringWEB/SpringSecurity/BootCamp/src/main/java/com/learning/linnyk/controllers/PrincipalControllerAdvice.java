package com.learning.linnyk.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class PrincipalControllerAdvice {

    @ModelAttribute("currentUser")
    Principal principal(Principal principal) {
        return principal;
    }

}
