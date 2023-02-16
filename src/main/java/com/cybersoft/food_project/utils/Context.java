package com.cybersoft.food_project.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class Context {
    public String getEmail() {
        var authentication = getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken ? null
                :authentication.getPrincipal().toString();
    }
}
