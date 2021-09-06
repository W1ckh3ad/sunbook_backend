package de.sunbook.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
public class UsersController {

    @GetMapping(value = "/")
    public String get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + auth.getAuthorities();
    }

}
