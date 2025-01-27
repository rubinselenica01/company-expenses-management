package com.company.expenses_management.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class SecurityUtils {

    public static UUID getLoggedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        //(JWTs) claims are pieces of information asserted about a subject
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            // handle jwt profile
            return getLoggedUser();
        } else {
            //handle null authentication
            return null;
        }
    }

    public static UUID getLoggedUser() {
        var authentication = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(authentication.getClaim("sub"));
    }
}
