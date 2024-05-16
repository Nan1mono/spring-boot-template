package com.project.template.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.module.system.model.entity.RoleMenu;
import com.project.template.security.entity.SecurityRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-菜单关系表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-27
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据菜单集合查询相应的菜单集合和其子选项
     *
     * @param roleIdList 角色id集合
     * @return {@link List}<{@link SecurityRoleMenu}>
     */
    List<SecurityRoleMenu> findRoleMenu(@Param("roleIdList") List<Long> roleIdList);

    /**
     * 根据父级id查询其下的子菜单
     *
     * @param parentId 父级id
     * @return {@link List}<{@link SecurityRoleMenu}>
     */
    List<SecurityRoleMenu> listSecurityRoleMenuByParentId(@Param("parentId") Long parentId);

}
