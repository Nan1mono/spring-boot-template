package com.project.template.common.util;

import com.project.template.module.system.model.entity.User;
import com.project.template.security.entity.SecurityUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

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

    public static String encryptPassword(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    public static boolean matchesPassword(String password, String encryptPassword){
        return PASSWORD_ENCODER.matches(password, encryptPassword);
    }

}
