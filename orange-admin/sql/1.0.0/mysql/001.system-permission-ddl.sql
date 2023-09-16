-- ---------------------------------------
-- orange-module-system-permission 服务依赖MySql表结构
-- ---------------------------------------

CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 部门信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_department`
(
    `id`         bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20)   NOT NULL COMMENT '租户id',
    `name`       varchar(128) NOT NULL DEFAULT '' COMMENT '名称',
    `parent_id`  bigint(20)   NOT NULL DEFAULT '-1' COMMENT '父级id,根节点的id为-1',
    `sort`       int                   DEFAULT 0 COMMENT '显示顺序',
    `created_by` bigint(20)   NOT NULL COMMENT '创建人',
    `created_at` datetime     NOT NULL COMMENT '创建时间',
    `updated_by` bigint(20)            DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime              DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '部门表';

-- ----------------------------
-- 登陆用户信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `department_id` bigint(20)   DEFAULT NULL COMMENT '部门ID',
    `avatar`        bigint(20)   DEFAULT NULL COMMENT '头像 存储文件ID',
    `name`          varchar(64)  DEFAULT NULL COMMENT '用户昵称',
    `email`         varchar(64)  DEFAULT NULL COMMENT '邮箱',
    `sex`           tinyint(1)   DEFAULT NULL COMMENT '用户性别，字典:sys_common_user_sex',
    `phone`         varchar(16)  DEFAULT NULL COMMENT '手机号',
    `username`      varchar(64)  DEFAULT NULL COMMENT '登陆账号',
    `password`      varchar(256) DEFAULT NULL COMMENT '密码',
    `remark`        varchar(256) DEFAULT NULL COMMENT '备注',
    `created_by`    bigint(20) NOT NULL COMMENT '创建人',
    `created_at`    datetime   NOT NULL COMMENT '创建时间',
    `updated_by`    bigint(20)   DEFAULT NULL COMMENT '更新人',
    `updated_at`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '登陆用户信息表';

-- ----------------------------
-- 用户角色关系表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_user_role_rl
(
    `id`         bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20) NOT NULL COMMENT '租户id',
    `user_id`    bigint(20) NOT NULL COMMENT '用户ID',
    `role_id`    bigint(20) NOT NULL COMMENT '角色ID',
    `created_by` bigint(20) NOT NULL COMMENT '创建人',
    `created_at` datetime   NOT NULL COMMENT '创建时间',
    `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户角色关系表';

-- ----------------------------
-- 角色信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_role
(
    `id`          bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20)   NOT NULL COMMENT '租户id',
    `name`        varchar(64)  NOT NULL COMMENT '角色名称',
    `permission`  varchar(128) NOT NULL COMMENT '权限编码',
    `sort`        int          DEFAULT 0 COMMENT '显示顺序',
    `status`      tinyint(1)   DEFAULT 0 COMMENT '状态,字典:sys_common_data_status',
    `preset_flag` tinyint(1)   DEFAULT 1 COMMENT '预设标志（0:预置 1:自定义）',
    `remark`      varchar(256) DEFAULT NULL COMMENT '备注',
    `created_by`  bigint(20)   NOT NULL COMMENT '创建人',
    `created_at`  datetime     NOT NULL COMMENT '创建时间',
    `updated_by`  bigint(20)   DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '角色信息表';

-- ----------------------------
-- 角色和资源关系表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_role_resource_rl
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `role_id`       bigint(20) NOT NULL COMMENT '角色ID',
    `resource_id`   bigint(20) DEFAULT NULL COMMENT '资源ID',
    `resource_type` tinyint    DEFAULT NULL COMMENT '资源类型 1 菜单 2 按钮',
    `created_by`    bigint(20) NOT NULL COMMENT '创建人',
    `created_at`    datetime   NOT NULL COMMENT '创建时间',
    `updated_by`    bigint(20) DEFAULT NULL COMMENT '更新人',
    `updated_at`    datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '角色和资源关系表';

-- ----------------------------
-- 菜单管理表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_menu
(
    `id`          bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20) NOT NULL COMMENT '租户id',
    `parent_id`   bigint(20) NOT NULL DEFAULT '-1' COMMENT '父级id,根节点的id为-1',
    `root_id`     bigint(20)          DEFAULT '-1' COMMENT '根节点ID,租户拷贝使用',
    `name`        varchar(128)        DEFAULT NULL COMMENT '名称',
    `permission`  varchar(128)        DEFAULT NULL COMMENT '权限编码,必须唯一',
    `preset_flag` tinyint             DEFAULT 1 COMMENT '预设标志（0:预置 1:自定义）',
    `path`        varchar(256)        DEFAULT NULL COMMENT '前端菜单路径',
    `icon`        varchar(64)         DEFAULT NULL COMMENT '图标',
    `hidden`      tinyint             DEFAULT 0 COMMENT '隐藏标志（0:不隐藏 1:隐藏）',
    `sort`        int                 DEFAULT 0 COMMENT '显示顺序',
    `remark`      varchar(256)        DEFAULT NULL COMMENT '备注',
    `created_by`  bigint(20) NOT NULL COMMENT '创建人',
    `created_at`  datetime   NOT NULL COMMENT '创建时间',
    `updated_by`  bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime            DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '菜单管理表';

-- ----------------------------
-- 按钮管理表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_button
(
    `id`          bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20) NOT NULL COMMENT '租户id',
    `menu_id`     bigint(20) NOT NULL COMMENT '菜单ID',
    `root_id`     bigint(20)   DEFAULT '-1' COMMENT '根节点ID,租户拷贝使用',
    `name`        varchar(128) DEFAULT NULL COMMENT '名称',
    `permission`  varchar(128) DEFAULT NULL COMMENT '权限编码，必须唯一',
    `preset_flag` tinyint      DEFAULT 1 COMMENT '预设标志（0:预置 1:自定义）',
    `sort`        int          DEFAULT 0 COMMENT '显示顺序',
    `remark`      varchar(256) DEFAULT NULL COMMENT '备注',
    `created_by`  bigint(20) NOT NULL COMMENT '创建人',
    `created_at`  datetime   NOT NULL COMMENT '创建时间',
    `updated_by`  bigint(20)   DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '按钮管理表';