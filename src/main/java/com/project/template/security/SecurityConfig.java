package com.project.template.security;

import com.project.template.model.entity.User;
import com.project.template.security.entity.SecurityUserDetail;
import com.project.template.security.enums.AuthFailEnum;
import com.project.template.security.exception.LoginException;
import com.project.template.security.filter.PermissionFilter;
import com.project.template.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    private final PermissionFilter permissionFilter;

    public SecurityConfig(UserService userService, PermissionFilter permissionFilter) {
        this.userService = userService;
        this.permissionFilter = permissionFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(permissionFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    /**
     * 配置Spring security密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.lambdaQuery()
                    .eq(User::getUsername, username)
                    .one();
            if (user != null) {
                return new SecurityUserDetail(user);
            }
            throw new LoginException(AuthFailEnum.FETCH_USERINFO_ERROR);
        };
    }

}
