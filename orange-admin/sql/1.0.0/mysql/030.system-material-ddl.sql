-- ---------------------------------------
-- orange-module-system-material 服务依赖MySql表结构
-- ---------------------------------------
CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 文件管理
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_file_management`
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `config_id`     bigint(20)    DEFAULT NULL COMMENT '配置编号',
    `original_name` varchar(256)  DEFAULT NULL COMMENT '文件原来名称',
    `new_name`      varchar(256)  DEFAULT NULL COMMENT '文件新名称,包含相对路径',
    `file_type`     varchar(64)   DEFAULT null COMMENT '文件类型',
    `file_size`     bigint        DEFAULT NULL COMMENT '文件大小',
    `url`           varchar(1024) DEFAULT NULL COMMENT '文件相对路径,S3路径',
    `created_by`    bigint(20)    DEFAULT NULL COMMENT '创建人',
    `created_at`    bigint(20)    DEFAULT NULL COMMENT '创建时间',
    `updated_by`    bigint(20)    DEFAULT NULL COMMENT '更新人',
    `updated_at`    bigint(20)    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='文件管理';


-- ----------------------------
-- 文件配置管理
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_file_config`
(
    `id`           bigint(20)  NOT NULL COMMENT '表的主键',
    `tenant_id`    bigint(20)  NOT NULL COMMENT '租户id',
    `storage_type` varchar(32) NOT NULL COMMENT '存储类型',
    `name`         varchar(256)  DEFAULT NULL COMMENT '配置名称',
    `config`       varchar(2048) DEFAULT NULL COMMENT '配置 json',
    `master`       tinyint(1)    DEFAULT 0 COMMENT '是否为默认配置 1为true，0为false',
    `created_by`   bigint(20)    DEFAULT NULL COMMENT '创建人',
    `created_at`   bigint(20)    DEFAULT NULL COMMENT '创建时间',
    `updated_by`   bigint(20)    DEFAULT NULL COMMENT '更新人',
    `updated_at`   bigint(20)    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='文件配置管理';


-- ----------------------------
-- 素材目录表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_material_category`
(
    `id`         bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20)   NOT NULL COMMENT '租户id',
    `name`       varchar(128) NOT NULL DEFAULT '' COMMENT '名称',
    `parent_id`  bigint(20)   NOT NULL DEFAULT '-1' COMMENT '父级id,根节点的id为-1',
    `ancestors`  varchar(512)          DEFAULT '' comment '祖级列表',
    `sort`       int                   DEFAULT 0 COMMENT '显示顺序',
    `created_by` bigint(20)            DEFAULT NULL COMMENT '创建人',
    `created_at` bigint(20)            DEFAULT NULL COMMENT '创建时间',
    `updated_by` bigint(20)            DEFAULT NULL COMMENT '更新人',
    `updated_at` bigint(20)            DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '素材目录表';
