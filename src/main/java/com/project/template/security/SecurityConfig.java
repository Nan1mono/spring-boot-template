package com.project.template.security;

import com.project.template.common.exception.MyException;
import com.project.template.common.result.ResultCodeEnum;
import com.project.template.model.entity.User;
import com.project.template.security.entity.SecurityUserDetail;
import com.project.template.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login/authTest")
                .hasAuthority("menu_on_page_one_add_button:view");
        return http.build();
    }


    /**
     * 配置Spring security密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
            throw new MyException(ResultCodeEnum.PASSWORD_ERROR);
        };
    }

    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfiguration();
    }

//    @Bean
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/res/**", "/login/login*").permitAll()
//                .antMatchers("/demo/user-list").access("hasRole('ROLE_Admin')")
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login/login").defaultSuccessUrl("/")
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and().logout().logoutSuccessUrl("/login/login");
//    }

}
