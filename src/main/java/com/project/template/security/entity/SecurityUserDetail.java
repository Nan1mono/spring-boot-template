package com.project.template.security.entity;

import com.project.template.common.constant.UserStatusEnum;
import com.project.template.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
public class SecurityUserDetail implements UserDetails {

    private final User user;

    @Setter
    private String token;

    public SecurityUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
}
