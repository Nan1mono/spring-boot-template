package com.project.template.security.filter;

import com.project.template.common.exception.MyException;
import com.project.template.common.helper.JwtHelper;
import com.project.template.common.helper.LocalCacheHelper;
import com.project.template.common.result.ResultCodeEnum;
import com.project.template.security.entity.SecurityUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PermissionFilter extends OncePerRequestFilter {

    @Value("${template.security.allow.uri}")
    private List<String> uri;

    private final PathMatcher matcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 路由白名单过滤
        if (request.getMethod().equals(HttpMethod.OPTIONS.name()) || uri.stream().anyMatch(t -> matcher.match(t, request.getRequestURI()))) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        Object userId = JwtHelper.getUserId(token);
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
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        filterChain.doFilter(request, response);
    }
}
