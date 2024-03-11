package com.backendapplication.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationService {

    public static String getUserRole() {
        // Get the current authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the user's authorities (roles)
            return authentication.getAuthorities().iterator().next().getAuthority();
        } else {
            // If the user is not authenticated, return null or an appropriate default value
            return null;
        }
    }
}
