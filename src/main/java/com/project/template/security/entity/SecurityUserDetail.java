package com.project.template.security.entity;

import com.project.template.common.constant.UserStatusEnum;
import com.project.template.module.system.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityUserDetail implements UserDetails {

    @Getter
    @Setter
    private final User user;

    @Setter
    @Getter
    private String token;

    @Setter
    @Getter
    private transient List<SecurityButton> buttonList;

    @Setter
    @Getter
    private transient List<SecurityUserRole> userRoleList;

    @Setter
    @Getter
    private transient List<SecurityRoleMenu> roleMenuList;

    private List<SimpleGrantedAuthority> authorities;

    public SecurityUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> permissionList) {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        permissionList.forEach(t -> authorityList.add(new SimpleGrantedAuthority(t)));
        this.authorities = authorityList;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未被锁定
    @Override
    public boolean isAccountNonLocked() {
        return this.user.getIsDeleted().equals(UserStatusEnum.UNDELETED.getCode());
    }

    /**
     * 凭据是否未过期
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return this.user.getStatus().equals(UserStatusEnum.ENABLE.getCode());
    }

    public String getUserInfo(String column) {
        UserColumn userColumn = UserColumn.valueOf(column.toUpperCase());
        switch (userColumn) {
            case USER_ID:
                return String.valueOf(this.user.getId());
            case NICKNAME:
                return this.user.getNickname();
            case REAL_NAME:
                return this.user.getRealName();
            case USERNAME:
                return this.user.getUsername();
            default:
                return "";
        }
    }

    @Getter
    enum UserColumn {
        USER_ID("user_id"),
        NICKNAME("nickname"),
        REAL_NAME("real_name"),
        USERNAME("username"),
        ;

        private final String column;

        UserColumn(String column) {
            this.column = column;
        }
    }

    public static String getUserCacheKey(Object userId) {
        return "template:login:user:" + userId;
    }
}
