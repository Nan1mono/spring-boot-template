package com.project.template.security.provider;

import com.project.template.common.constant.UserStatusEnum;
import com.project.template.common.helper.JwtHelper;
import com.project.template.common.helper.LocalCacheHelper;
import com.project.template.mapper.RoleMenuMapper;
import com.project.template.mapper.RolePermissionMapper;
import com.project.template.mapper.UserButtonMapper;
import com.project.template.mapper.UserRoleMapper;
import com.project.template.model.entity.User;
import com.project.template.security.entity.SecurityRoleMenu;
import com.project.template.security.entity.SecurityUserDetail;
import com.project.template.security.entity.SecurityUserRole;
import com.project.template.security.enums.LoginFailEnum;
import com.project.template.security.exception.LoginException;
import com.project.template.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义密码校验流程
 */
@Service
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Value("${template.token.expiration:25200}")
    private long tokenExpiration;

    @Value("${template.token.sign-key:nan1mono}")
    private String tokenSignKey;

    @Value("${template.security.button-enable:true}")
    private Boolean isFind;

    @Value("${template.pass-error.enable:false}")
    private Boolean isCheckLock;

    @Value("${template.pass-error.times:5}")
    private Integer times;

    @Value("${template.pass-error.lock-minute:15}")
    private Long minute;

    @Resource
    private UserButtonMapper userButtonMapper;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private UserService userService;

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
        // 检查账户是否被锁定
        if (user.getIsLocked().equals(UserStatusEnum.LOCKED.getCode())) {
            LocalDateTime lockDatetime = user.getLockDatetime();
            // 如果当前时间已经超过解锁时间，则自动解锁并清空解锁时间，同时清楚缓存中的试错计数，否则提示已锁定和解锁时间
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(lockDatetime)) {
                userService.lambdaUpdate()
                        .eq(User::getId, user.getId())
                        .set(User::getIsLocked, UserStatusEnum.UN_LOCKED.getCode())
                        .set(User::getLockDatetime, null)
                        .update();
                LocalCacheHelper.remove(this.getUserErrorPassLockNumKey(user.getId()));
            } else {
                throw new LoginException(lockMsg(lockDatetime), 400);
            }
        }
        // 如果时间存在，则代表存在过期时间
        if (pwdExpirationTime != null) {
            boolean before = pwdExpirationTime.isBefore(LocalDateTime.now());
            if (!before) {
                throw new LoginException(LoginFailEnum.PASSWORD_EXPIRATION);
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
            this.countPassErrorTimes(isCheckLock, securityUserDetail.getUser());
            throw new LoginException(LoginFailEnum.PASSWORD_ERROR);
        }
        // 校验其他规则
        this.additionalAuthenticationChecks(securityUserDetail, (UsernamePasswordAuthenticationToken) authentication);
        // 校验通过
        User user = securityUserDetail.getUser();
        String token = JwtHelper.createToken(user.getId(), user.getUsername(), user.getNickname(), user.getRealName(), tokenExpiration, tokenSignKey);
        securityUserDetail.setToken(token);
        // 检索用户对应的权限按钮
        isFindUserButton(isFind == null || isFind, securityUserDetail);
        // 检索用户对应的角色集合
        findUserRole(securityUserDetail);
        // 用户登录信息添加至本地缓存
        LocalCacheHelper.remove(securityUserDetail.getUser().getId());
        LocalCacheHelper.put(securityUserDetail.getUser().getId(), securityUserDetail);
        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken
                .authenticated(securityUserDetail,
                        securityUserDetail.getUser().getPassword(), securityUserDetail.getAuthorities());
        // 设定密码为空
        securityUserDetail.getUser().setPassword(null);
        return authenticated;
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

    private void findUserRole(SecurityUserDetail securityUserDetail) {
        Long userId = securityUserDetail.getUser().getId();
        List<SecurityUserRole> userRoleList = userRoleMapper.findUserRole(userId);
        securityUserDetail.setUserRoleList(userRoleList);
        List<Long> roleIdList = userRoleList.stream()
                .map(SecurityUserRole::getRoleId)
                .distinct()
                .collect(Collectors.toList());
        securityUserDetail.setRoleMenuList(findUserMenu(roleIdList));
        securityUserDetail.setAuthorities(findUserPermission(roleIdList));
    }

    private List<SecurityRoleMenu> findUserMenu(List<Long> roleIdList) {
        return roleMenuMapper.findRoleMenu(roleIdList);
    }

    private List<String> findUserPermission(List<Long> roleIdList) {
        return rolePermissionMapper.findUserPermission(roleIdList);
    }

    private void countPassErrorTimes(boolean isCheck, User user) {
        if (!isCheck) {
            return;
        }
        // 如果账号已经锁定，则不需要在进行锁定检查了
        if (user.getIsLocked().equals(UserStatusEnum.LOCKED.getCode())) {
            throw new LoginException(lockMsg(user.getLockDatetime()), 400);
        }
        String userErrorPassLockNumKey = this.getUserErrorPassLockNumKey(user.getId());
        // 获取登录错误计数
        Object lockNumObject = LocalCacheHelper.getIfPresent(userErrorPassLockNumKey);
        int lockCount = 0;
        if (ObjectUtils.isNotEmpty(lockNumObject)) {
            lockCount = Integer.parseInt(lockNumObject.toString());
        }
        // 判断是否已达设定的上限，如果未达上限，仅需增加试错次数
        if (lockCount < times) {
            lockCount += 1;
            LocalCacheHelper.put(userErrorPassLockNumKey, lockCount);
            return;
        }
        // 如果已达上限则需要更新用户信息，包括数据库信息
        user.setIsLocked(UserStatusEnum.LOCKED.getCode());
        user.setLockDatetime(LocalDateTime.now().plusMinutes(minute));
        userService.lambdaUpdate()
                .eq(User::getId, user.getId())
                .set(User::getIsLocked, user.getIsLocked())
                .set(User::getLockDatetime, user.getLockDatetime())
                .update();
    }

    private String getUserErrorPassLockNumKey(Long userId) {
        return "user:error-pass:error-num:" + userId;
    }

    public static String lockMsg(LocalDateTime lockDatetime) {
        return lockDatetime == null ?
                "您的账户已被永久锁定，如果疑问请联系管理员" :
                "您的账号被锁定至 "
                        + lockDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        + "，如果有疑问请联系管理员";
    }
}
