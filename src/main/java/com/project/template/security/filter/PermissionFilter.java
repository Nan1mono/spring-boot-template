package com.project.template.security.filter;

import com.project.template.common.cache.CacheTemplateManager;
import com.project.template.common.constant.UserStatusEnum;
import com.project.template.common.helper.JwtHelper;
import com.project.template.security.entity.SecurityUserDetail;
import com.project.template.security.enums.LoginFailEnum;
import com.project.template.security.provider.CustomAuthenticationProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
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

    @Resource
    private CacheTemplateManager cacheTemplateManager;

    private final PathMatcher matcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 初始化缓存管理器
        cacheTemplateManager = cacheTemplateManager.createManager();
        // 路由白名单过滤
        if (request.getMethod().equals(HttpMethod.OPTIONS.name()) || uri.stream().anyMatch(t -> matcher.match(t, request.getRequestURI()))) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        // 验证token是否存在
        if (StringUtils.isBlank(token)) {
            throw new BadCredentialsException(LoginFailEnum.LOGIN_AUTH.getMessage());
        }
        token = token.replace("Bearer ", "");
        Object userId = JwtHelper.getUserId(token);
        if (userId == null) {
            throw new BadCredentialsException(LoginFailEnum.LOGIN_AUTH.getMessage());
        }
        // 验证缓存token
        Object value = cacheTemplateManager.getIfPresent(SecurityUserDetail.getUserCacheKey(userId));
        if (value == null) {
            throw new BadCredentialsException(LoginFailEnum.LOGIN_AUTH.getMessage());
        }
        SecurityUserDetail userDetail = (SecurityUserDetail) value;
        // 验证token是否匹配
        if (!userDetail.getToken().equals(token)) {
            throw new BadCredentialsException(LoginFailEnum.LOGIN_EXPIRATION.getMessage());
        }
        // 验证用户是否被锁定
        if (userDetail.getUser().getIsLocked().equals(UserStatusEnum.LOCKED.getCode())) {
            throw new BadCredentialsException(CustomAuthenticationProvider.lockMsg(userDetail.getUser().getLockDatetime()));
        }
        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken
                .authenticated(userDetail, userDetail.getUser().getPassword(), userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        filterChain.doFilter(request, response);
    }
}
