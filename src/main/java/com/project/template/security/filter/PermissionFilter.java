package com.project.template.security.filter;

import com.project.template.common.exception.MyException;
import com.project.template.common.helper.JwtHelper;
import com.project.template.common.helper.LocalCacheHelper;
import com.project.template.common.result.ResultCodeEnum;
import com.project.template.security.entity.SecurityUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PermissionFilter extends OncePerRequestFilter {

    @Value("${template.token.sign-key}")
    private String tokenSignKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/login/auth") || request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        Object userId = JwtHelper.getUserId(token, tokenSignKey);
        if (userId == null) {
            throw new MyException(ResultCodeEnum.LOGIN_AUTH);
        }
        Object value = LocalCacheHelper.getIfPresent(Long.valueOf(userId.toString()));
        if (value == null) {
            throw new MyException(ResultCodeEnum.LOGIN_AUTH);
        }
        SecurityUserDetail userDetail = (SecurityUserDetail) value;
        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken
                .authenticated(userDetail, userDetail.getUser().getPassword(), userDetail.getAuthorities());
        SecurityContextHolder.getContext()
                .setAuthentication(authenticated);
        filterChain.doFilter(request, response);
    }
}
