package com.company.expenses_management.security;

import com.company.expenses_management.model.entity.user.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class SecurityUtils {

    public static UUID getLoggedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            return getLoggedUser();
        } else {
            return null;
        }
    }

    public static UUID getLoggedUser() {
        var authentication = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(authentication.getClaim("sub"));
    }

    public static Role getLoggedUserRole() {
        var authentication = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Role.fromValue(authentication.getClaim("roles"));
    }

}
