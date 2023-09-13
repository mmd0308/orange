-- ---------------------------------------
-- orange-module-system-dict 服务依赖MySql表结构
-- ---------------------------------------
CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 字典类型表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_dict_type
(
    `id`          bigint(20)          NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20)          NOT NULL COMMENT '租户id',
    `name`        varchar(128) DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(128) UNIQUE NOT NULL COMMENT '字典类型,唯一',
    `status`      tinyint(1)   DEFAULT 0 COMMENT '状态',
    `preset_flag` tinyint(1)   DEFAULT 1 COMMENT '预设标志（0:预置 1:自定义）',
    `remark`      varchar(512) DEFAULT NULL COMMENT '备注',
    `created_by`  bigint(20)   DEFAULT NULL COMMENT '创建人',
    `created_at`  bigint(20)   DEFAULT NULL COMMENT '创建时间',
    `updated_by`  bigint(20)   DEFAULT NULL COMMENT '更新人',
    `updated_at`  bigint(20)   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='字典类型表';

-- ----------------------------
-- 字典数据表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_dict_data
(
    `id`           bigint       NOT NULL COMMENT '表的主键',
    `tenant_id`    bigint       NOT NULL COMMENT '租户id',
    `sort`         int          DEFAULT 0 COMMENT '字典排序',
    `dict_label`   varchar(100) DEFAULT '' COMMENT '字典数据标签',
    `dict_value`   varchar(64)  DEFAULT '' COMMENT '字典数据键值',
    `dict_type`    varchar(128) NOT NULL COMMENT '字典类型',
    `preset_flag`  tinyint      DEFAULT 1 COMMENT '预设标志（0:预置 1:自定义）',
    `default_flag` tinyint      DEFAULT 0 COMMENT '是否默认(0：否 1：是)',
    `show_style`   varchar(32)  DEFAULT '' COMMENT '回显样式',
    `status`       tinyint      DEFAULT NULL COMMENT '状态',
    `remark`       varchar(512) DEFAULT NULL COMMENT '备注',
    `created_by`   bigint       DEFAULT NULL COMMENT '创建人',
    `created_at`   bigint       DEFAULT NULL COMMENT '创建时间',
    `updated_by`   bigint       DEFAULT NULL COMMENT '更新人',
    `updated_at`   bigint       DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='字典数据表';

