-- ---------------------------------------
-- orange-module-system-info 服务依赖菜单
-- sys_menu ID 范围 [140, 160)
-- sys_button ID 范围 [140*100, 160*100+100)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- 系统管理菜单ID
SET @s_id := 1;

-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- 菜单和按钮数据预置
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (140, @t_id, -1, '信息管理', 'system:info', 0, '', 'system-library', 0, 30, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (141, @t_id, 140, '信息类型', 'system:info:type:view', 0, 'system/info/type', 'system-reader', 0, 10, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (14100, @t_id, 141, -1, '新增', 'system:info:type:add', 0, 1, '信息类型管理-新增', @u_id, NOW(), @u_id, NOW()),
       (14110, @t_id, 141, -1, '删除', 'system:info:type:delete', 0, 10, '信息类型管理-删除', @u_id, NOW(), @u_id, NOW()),
       (14120, @t_id, 141, -1, '修改', 'system:info:type:update', 0, 20, '信息类型管理-修改', @u_id, NOW(), @u_id, NOW())
;

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (142, @t_id, 140, '信息中心', 'system:info:manage:view', 0, 'system/info/info', 'system-reader', 0, 20, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (14200, @t_id, 142, -1, '新增', 'system:info:info:add', 0, 1, '信息中心-新增', @u_id, NOW(), @u_id, NOW()),
       (14210, @t_id, 142, -1, '删除', 'system:info:info:delete', 0, 10, '信息中心-删除', @u_id, NOW(), @u_id, NOW()),
       (14211, @t_id, 142, -1, '批量删除', 'system:info:info:delete', 0, 10, '信息中心-批量删除', @u_id, NOW(), @u_id, NOW()),
       (14220, @t_id, 142, -1, '修改', 'system:info:info:update', 0, 20, '信息中心-修改', @u_id, NOW(), @u_id, NOW())
;
COMMIT;