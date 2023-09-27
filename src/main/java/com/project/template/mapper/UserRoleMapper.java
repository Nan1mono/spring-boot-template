package com.project.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.model.entity.UserRole;
import com.project.template.security.entity.SecurityUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 账号/用户/管理员-角色关系表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-27
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据userId查询相应的角色集合
     *
     * @param userId 用户id
     * @return {@link List}<{@link SecurityUserRole}>
     */
    List<SecurityUserRole> findUserRole(@Param("userId") Long userId);

}
