-- ---------------------------------------
-- orange-module-system-info 服务依赖MySql表结构
-- ---------------------------------------
CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 类型
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_info_type
(
    `id`          bigint(20)          NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20)          NOT NULL COMMENT '租户id',
    `parent_id`   bigint(20)          NOT NULL DEFAULT '-1' COMMENT '父级id,根节点的id为-1',
    `name`        varchar(128)                 DEFAULT '' COMMENT '类型名称',
    `code`        varchar(128) UNIQUE NOT NULL COMMENT '类型编码',
    `sort`        int                          DEFAULT 0 COMMENT '排序',
    `preset_flag` tinyint(1)                   DEFAULT 1 COMMENT '预设标志（0:预置 1:自定义）',
    `remark`      varchar(512)                 DEFAULT NULL COMMENT '备注',
    `created_by`  bigint(20)          NOT NULL COMMENT '创建人',
    `created_at`  datetime            NOT NULL COMMENT '创建时间',
    `updated_by`  bigint(20)                   DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime                     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='类型';


-- ----------------------------
-- 信息管理
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_info
(
    `id`         bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20)   NOT NULL COMMENT '租户id',
    `type_id`    bigint(20) DEFAULT NULL COMMENT '类型ID',
    `title`      varchar(256) NOT NULL COMMENT '标题',
    `content`    text         NOT NULL COMMENT '内容',
    `sort`       int        DEFAULT 0 COMMENT '排序',
    `created_by` bigint(20)   NOT NULL COMMENT '创建人',
    `created_at` datetime     NOT NULL COMMENT '创建时间',
    `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='信息管理';
