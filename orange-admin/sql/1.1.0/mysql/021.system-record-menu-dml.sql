-- ---------------------------------------
-- orange-module-system-record 服务依赖菜单
-- sys_menu ID 范围 [120, 140)
-- sys_button ID 范围 [120*100, 140*100+100)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- 菜单和按钮数据预置
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 日志管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (120, @t_id, -1, '日志管理', 'system:record', 0, '', 'system-reader', 0, 120, null, @u_id, NOW(), @u_id, NOW());

-- 登录日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (121, @t_id, 120, '登陆日志', 'system:record:login:view', 0, 'system/permission/login-log', 'system-reader', 0, 10, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (12100, @t_id, 121, -1, '清空', 'system:record:login:clear', 0, 1, '登陆日志-清空操作日志', @u_id, NOW(), @u_id, NOW());

-- 操作日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (122, @t_id, 120, '操作日志', 'system:record:operation:view', 0, 'system/permission/operation-log', 'system-reader', 0, 20, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (12200, @t_id, 122, -1, '清空', 'system:record:operation:clear', 0, 1, '操作日志-清空操作日志', @u_id, NOW(), @u_id, NOW());
COMMIT;