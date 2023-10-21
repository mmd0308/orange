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
-- 日志管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (140, @t_id, -1, '消息管理', 'system:info', 0, '', 'system-reader', 0, 2, null, @u_id, NOW(), @u_id, NOW());

-- 登录日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (141, @t_id, 140, '消息类型', 'system:info:type:view', 0, 'system/info/type', 'system-reader', 0, 20, null, @u_id, NOW(), @u_id, NOW());

-- 操作日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (142, @t_id, 140, '消息管理', 'system:info:manage:view', 0, 'system/permission/operation-log', 'system-reader', 0, 18, null, @u_id, NOW(), @u_id, NOW());
COMMIT;