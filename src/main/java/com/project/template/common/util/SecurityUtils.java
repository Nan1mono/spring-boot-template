package com.project.template.common.util;

import com.project.template.model.entity.User;
import com.project.template.security.entity.SecurityUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {

    }

    public static SecurityUserDetail getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SecurityUserDetail) authentication.getPrincipal();
    }

    public static String getToken() {
        return getUserDetail().getToken();
    }

    public static User getUser() {
        return getUserDetail().getUser();
    }

}
