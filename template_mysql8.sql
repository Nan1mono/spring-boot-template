/*
 Navicat Premium Data Transfer

 Source Server         : mysql-192.168.66.130
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : 192.168.66.130:3306
 Source Schema         : template_mysql8

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 19/01/2024 23:04:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单id',
  `button_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '按钮编号',
  `button_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '按钮名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '按钮资源表,控制被限制的按钮' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_button
-- ----------------------------
INSERT INTO `sys_button` VALUES (1, NULL, 'menu_on_page_one_add_button', '菜单一:页面一:查看按钮', '菜单一:页面一:查看按钮', 1, 0, 'system', '2023-09-26 16:21:38', 'system', '2023-09-26 16:21:39');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父级菜单id',
  `level` int NOT NULL DEFAULT 1 COMMENT '菜单层级',
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主要路径',
  `uri` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由路径',
  `sorted` int NULL DEFAULT 1 COMMENT '顺序',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'menu_one', '菜单一', 0, 1, '/menu_one', NULL, 1, 1, 0, 'system', '2023-09-26 16:01:36', 'system', '2023-09-26 16:01:36');
INSERT INTO `sys_menu` VALUES (2, 'menu_two', '菜单二', 0, 1, '/menu_two', NULL, 2, 1, 0, 'system', '2023-09-26 16:01:36', 'system', '2023-09-26 16:01:36');
INSERT INTO `sys_menu` VALUES (3, 'menu_one_page_one', '菜单一:页面一', 1, 2, NULL, '/page_one', 1, 1, 0, 'system', '2023-09-26 16:01:36', 'system', '2023-09-26 16:01:36');
INSERT INTO `sys_menu` VALUES (4, 'menu_one_page_two', '菜单一:页面二', 1, 2, NULL, '/page_two', 1, 1, 0, 'system', '2023-09-26 16:01:36', 'system', '2023-09-26 16:01:36');
INSERT INTO `sys_menu` VALUES (5, 'menu_two_page_one', '菜单二:页面一', 2, 2, NULL, '/page_one', 1, 1, 0, 'system', '2023-09-26 16:01:36', 'system', '2023-09-26 16:01:36');
INSERT INTO `sys_menu` VALUES (6, 'menu_two_page_two', '菜单二:页面二', 2, 2, NULL, '/page_two', 1, 1, 0, 'system', '2023-09-26 16:01:36', 'system', '2023-09-26 16:01:36');

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operation_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作/选项编号',
  `operation_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作/选项名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '基础权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES (1, '查看', 'view', '查看权限', 1, 0, 'system', '2023-09-26 15:04:00', 'system', '2023-09-26 15:05:01');
INSERT INTO `sys_operation` VALUES (2, '新增', 'add', '新增权限', 1, 0, 'system', '2023-09-26 15:04:00', 'system', '2023-09-26 15:05:01');
INSERT INTO `sys_operation` VALUES (3, '编辑', 'edit', '编辑权限', 1, 0, 'system', '2023-09-26 15:04:00', 'system', '2023-09-26 15:05:01');
INSERT INTO `sys_operation` VALUES (4, '删除', 'delete', '删除权限', 1, 0, 'system', '2023-09-26 15:04:00', 'system', '2023-09-26 15:05:01');
INSERT INTO `sys_operation` VALUES (5, '导出', 'export', '导出/下载权限', 1, 0, 'system', '2023-09-26 15:04:00', 'system', '2023-09-26 15:05:01');
INSERT INTO `sys_operation` VALUES (6, '导入', 'import', '导入/上传权限', 1, 0, 'system', '2023-09-26 15:04:00', 'system', '2023-09-26 15:05:01');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '对应的资源编号',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源类型:menu-菜单资源,button-按钮资源,resource-自定义资源',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限编号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源权限表,包含所有资源和对应的权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 'menu_one_button_permission', 'button', 'menu_on_page_one_add_button:view', 1, 0, 'system', '2023-09-27 16:38:35', 'system', '2023-09-27 16:38:35');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint NOT NULL COMMENT 'id',
  `resource_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源编号',
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '自定义资源表,丰富权限控制体系,提供更加灵活的权限控制' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编号',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'Administrator', '管理员', '系统默认最高管理员', 1, 0, 'system', '2023-09-27 22:48:22', 'system', '2023-09-27 22:48:23');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色-菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, 0, 'system', '2023-09-27 22:48:48', 'system', '2023-09-27 22:48:48');
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, 0, 'system', '2023-09-27 22:48:48', 'system', '2023-09-27 22:48:48');
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, 0, 'system', '2023-09-27 22:48:48', 'system', '2023-09-27 22:48:48');
INSERT INTO `sys_role_menu` VALUES (4, 1, 4, 0, 'system', '2023-09-27 22:48:48', 'system', '2023-09-27 22:48:48');
INSERT INTO `sys_role_menu` VALUES (5, 1, 5, 0, 'system', '2023-09-27 22:48:48', 'system', '2023-09-27 22:48:48');
INSERT INTO `sys_role_menu` VALUES (6, 1, 6, 0, 'system', '2023-09-27 22:48:48', 'system', '2023-09-27 22:48:48');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `permission_id` bigint NOT NULL COMMENT '权限id',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色-权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1, 0, 'system', '2023-09-28 01:38:14', 'system', '2023-09-28 01:38:14');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名/账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `pwd_expiration_time` datetime NULL DEFAULT NULL COMMENT '密码到期时间 空代表永不过期',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 1启用 0停用',
  `is_locked` int NULL DEFAULT 0 COMMENT '是否被锁定 0未锁定 1锁定',
  `lock_datetime` datetime NULL DEFAULT NULL COMMENT '账号锁定到期时间，空代表永久锁定',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '账号/用户/管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'lee', 'lee', '2000-04-19', 'admin', '$2a$10$A6D78JEKYB3OSiCRUTRS0eDfuzg6las3PtTffdART2LJLbG2IMnIy', NULL, 1, 0, NULL, 0, NULL, '2023-09-22 09:38:38', NULL, '2023-09-22 09:38:38');
INSERT INTO `sys_user` VALUES (2, 'lin', 'lin', '2000-04-19', 'adminlin', '123456', NULL, 1, 0, NULL, 0, NULL, '2023-09-22 17:47:25', NULL, '2023-09-22 17:47:27');
INSERT INTO `sys_user` VALUES (3, 'jolin', 'jolin', '2000-04-19', 'adminjolin', '123456', NULL, 1, 0, NULL, 0, NULL, '2023-09-22 17:47:52', NULL, '2023-09-22 17:47:54');
INSERT INTO `sys_user` VALUES (4, 'Koon Ching Wan', 'Koon Ching Wan', '2021-01-03', 'Koon Ching Wan', 'ZItOLKIjc9', NULL, 1, 0, NULL, 0, 'vTP8Aj6mND', '2019-10-06 05:30:48', 'vTP8Aj6mND', '2013-06-17 18:46:41');
INSERT INTO `sys_user` VALUES (5, 'Chin Ming', 'Chin Ming', '2000-04-19', 'Chin Ming', 'Wb3xX6YYLt', NULL, 1, 0, NULL, 0, 'XUlOjDBIel', '2011-03-14 08:20:37', 'XUlOjDBIel', '2017-02-23 03:17:12');
INSERT INTO `sys_user` VALUES (6, 'Takeda Daichi', 'Takeda Daichi', '2008-10-06', 'Takeda Daichi', 'VQ7h2vNxNX', NULL, 1, 0, NULL, 0, 'j6MvL5ZtIq', '2015-06-01 16:18:12', 'j6MvL5ZtIq', '2009-01-28 00:40:54');
INSERT INTO `sys_user` VALUES (7, 'Kato Takuya', 'Kato Takuya', '2017-03-19', 'Kato Takuya', '5aIiVpuuqW', NULL, 1, 0, NULL, 0, 'qloP4jUSeO', '2021-10-26 05:32:03', 'qloP4jUSeO', '2000-08-26 14:07:35');
INSERT INTO `sys_user` VALUES (8, 'Luis Ramos', 'Luis Ramos', '2011-02-14', 'Luis Ramos', 'KvTqhgmxYS', NULL, 1, 0, NULL, 0, 'iOInHQf7ZI', '2023-01-08 19:23:55', 'iOInHQf7ZI', '2004-03-27 22:37:31');
INSERT INTO `sys_user` VALUES (9, 'Maeda Mai', 'Maeda Mai', '2010-08-06', 'Maeda Mai', 'CIODgorM0e', NULL, 1, 0, NULL, 0, 'woNbHb0fsX', '2004-05-27 16:23:59', 'woNbHb0fsX', '2003-10-15 03:35:41');
INSERT INTO `sys_user` VALUES (10, 'Lei Zitao', 'Lei Zitao', '2002-09-19', 'Lei Zitao', 'WqRB9vUr8s', NULL, 1, 0, NULL, 0, '9wvHFPpuWg', '2017-06-21 06:17:43', '9wvHFPpuWg', '2005-12-01 19:54:29');
INSERT INTO `sys_user` VALUES (11, 'Debbie Butler', 'Debbie Butler', '2020-12-29', 'Debbie Butler', '8iEfbElXKD', NULL, 1, 0, NULL, 0, '4x71EWUfeM', '2019-07-29 18:16:43', '4x71EWUfeM', '2002-03-17 05:38:00');
INSERT INTO `sys_user` VALUES (12, 'Nishimura Ayano', 'Nishimura Ayano', '2005-02-18', 'Nishimura Ayano', '6DUJL7Sw49', NULL, 1, 0, NULL, 0, 'qoNqJnjwSn', '2007-09-22 23:29:47', 'qoNqJnjwSn', '2022-12-15 02:58:40');
INSERT INTO `sys_user` VALUES (13, 'Ikeda Misaki', 'Ikeda Misaki', '2002-04-15', 'Ikeda Misaki', 'XEs0jmMdHv', NULL, 1, 0, NULL, 0, 'hgyQ0tZXTL', '2003-02-03 20:44:20', 'hgyQ0tZXTL', '2005-02-07 09:35:26');
INSERT INTO `sys_user` VALUES (14, 'Carmen Davis', 'Carmen Davis', '2017-11-06', 'Carmen Davis', 'Gad6QOqSk4', NULL, 1, 0, NULL, 0, '2ZVBgA0skD', '2000-02-09 06:18:34', '2ZVBgA0skD', '2021-02-13 06:04:23');
INSERT INTO `sys_user` VALUES (15, 'Matsumoto Minato', 'Matsumoto Minato', '2004-06-18', 'Matsumoto Minato', 'LbRHUmgkWk', NULL, 1, 0, NULL, 0, 'rbZYRWvXcf', '2010-07-30 07:56:03', 'rbZYRWvXcf', '2004-07-16 05:30:46');
INSERT INTO `sys_user` VALUES (16, 'Tian Jialun', 'Tian Jialun', '2002-11-24', 'Tian Jialun', 'xPQqOQ6Kxb', NULL, 1, 0, NULL, 0, 'KCMzWbYQfL', '2008-10-11 16:00:52', 'KCMzWbYQfL', '2022-11-01 17:21:40');
INSERT INTO `sys_user` VALUES (17, 'Leroy Jenkins', 'Leroy Jenkins', '2014-02-20', 'Leroy Jenkins', 'aVUdgm3nHZ', NULL, 1, 0, NULL, 0, '2E2pVp46ef', '2005-09-07 17:07:49', '2E2pVp46ef', '2017-04-05 13:06:33');
INSERT INTO `sys_user` VALUES (18, 'Hirano Takuya', 'Hirano Takuya', '2018-04-09', 'Hirano Takuya', 'soVeKTxl2v', NULL, 1, 0, NULL, 0, 'OTwgpDMnFP', '2022-07-31 00:52:53', 'OTwgpDMnFP', '2014-11-17 04:47:22');

-- ----------------------------
-- Table structure for sys_user_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_button`;
CREATE TABLE `sys_user_button`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `button_id` bigint NOT NULL COMMENT '按钮id',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '账号/用户/管理员-角按钮关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_button
-- ----------------------------
INSERT INTO `sys_user_button` VALUES (1, 1, 1, 0, 'system', '2023-09-26 17:40:48', 'system', '2023-09-26 17:40:53');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '删除标志 0未删除 1删除',
  `update_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_on` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '账号/用户/管理员-角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, 0, 'system', '2023-09-27 22:49:45', 'system', '2023-09-27 22:49:47');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
