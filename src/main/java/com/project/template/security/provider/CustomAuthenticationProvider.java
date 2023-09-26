package com.project.template.security.provider;

import com.google.common.collect.Lists;
import com.project.template.common.exception.MyException;
import com.project.template.common.helper.JwtHelper;
import com.project.template.common.result.ResultCodeEnum;
import com.project.template.mapper.UserButtonMapper;
import com.project.template.model.entity.User;
import com.project.template.security.entity.SecurityUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 自定义密码校验流程
 */
@Service
public abstract class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Value("${template.token.expiration}")
    private long tokenExpiration;

    @Value("${template.token.sign-key}")
    private String tokenSignKey;

    @Value("${template.security.button-enable}")
    private Boolean isFind;

    @Resource
    private UserButtonMapper userButtonMapper;

    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 其他身份验证检查
     *
     * @param userDetails    用户详细信息
     * @param authentication 认证
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        SecurityUserDetail securityUserDetail = (SecurityUserDetail) userDetails;
        User user = securityUserDetail.getUser();
        LocalDateTime pwdExpirationTime = user.getPwdExpirationTime();
        // 如果时间存在，则代表存在过期时间
        if (pwdExpirationTime != null) {
            boolean before = pwdExpirationTime.isBefore(LocalDateTime.now());
            if (!before) {
                throw new MyException(ResultCodeEnum.PASSWORD_EXPIRATION);
            }
        }
    }


    /**
     * 检索用户
     *
     * @param username       用户名
     * @param authentication 认证
     * @return {@link UserDetails}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected SecurityUserDetail retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return (SecurityUserDetail) userDetailsService.loadUserByUsername(username);
    }

    /**
     * 重写身份检查 密码校验规则
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        // 获取登录时暗文密码
        String presentedPassword = authentication.getCredentials().toString();
        String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        SecurityUserDetail securityUserDetail =
                this.retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
        // 匹配密码
        if (!presentedPassword.equals(securityUserDetail.getPassword())) {
            throw new MyException(ResultCodeEnum.PASSWORD_ERROR);
        }
        // 校验其他规则
        this.additionalAuthenticationChecks(securityUserDetail, (UsernamePasswordAuthenticationToken) authentication);
        // 校验通过
        User user = securityUserDetail.getUser();
        String token = JwtHelper.createToken(user.getId(), user.getUsername(), user.getNickname(), user.getRealName(), tokenExpiration, tokenSignKey);
        securityUserDetail.setToken(token);
        // 检索用户对应的权限按钮
        isFindUserButton(isFind == null || isFind, securityUserDetail);
        // TODO date: 2023-09-24 01:25:02    description: 补充权限接口，增加缓存机制
        return UsernamePasswordAuthenticationToken.authenticated(securityUserDetail, null, Lists.newArrayList((GrantedAuthority) () -> "test"));
    }

    /**
     * 验证支持
     * 本Provider只支持UsernamePasswordAuthenticationToken的验证
     *
     * @param authentication 认证
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private void isFindUserButton(boolean isFind, SecurityUserDetail securityUserDetail) {
        if (isFind) {
            User user = securityUserDetail.getUser();
            securityUserDetail.setButtonList(userButtonMapper.findUserButton(user.getId()));
        }
    }

    private void findUserMenu(SecurityUserDetail securityUserDetail) {

    }
}
