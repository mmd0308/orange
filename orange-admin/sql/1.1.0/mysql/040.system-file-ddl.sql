-- ---------------------------------------
-- orange-module-system-file 服务依赖MySql表结构
-- ---------------------------------------
CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 文件配置
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_file_config`
(
    `id`         bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20)   NOT NULL COMMENT '租户id',
    `storage`    varchar(32)  NOT NULL COMMENT '存储器',
    `name`       varchar(256) NOT NULL COMMENT '配置名称',
    `config`     text         DEFAULT NULL COMMENT '配置 json',
    `master`     tinyint(1)   DEFAULT 0 COMMENT '是否为默认配置 1为true，0为false',
    `remark`     varchar(512) DEFAULT NULL COMMENT '备注',
    `created_by` bigint(20)   NOT NULL COMMENT '创建人',
    `created_at` datetime     NOT NULL COMMENT '创建时间',
    `updated_by` bigint(20)   DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='文件配置管理';


-- ----------------------------
-- 文件管理
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_file`
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `config_id`     bigint(20)    DEFAULT NULL COMMENT '配置编号',
    `original_name` varchar(256)  DEFAULT NULL COMMENT '文件原来名称',
    `name`          varchar(256)  DEFAULT NULL COMMENT '文件新名称',
    `path`          varchar(256)  DEFAULT NULL COMMENT '文件路径',
    `type`          varchar(128)  DEFAULT null COMMENT '文件类型',
    `size`          bigint(20)    DEFAULT NULL COMMENT '文件大小',
    `url`           varchar(1024) DEFAULT NULL COMMENT '文件相对路径,S3路径',
    `created_by`    bigint(20) NOT NULL COMMENT '创建人',
    `created_at`    datetime   NOT NULL COMMENT '创建时间',
    `updated_by`    bigint(20)    DEFAULT NULL COMMENT '更新人',
    `updated_at`    datetime      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='文件管理';
