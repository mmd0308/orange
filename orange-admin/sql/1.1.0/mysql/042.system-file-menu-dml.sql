-- ---------------------------------------
-- orange-module-system-file 服务依赖菜单
-- sys_menu ID 范围 [160, 180)
-- sys_button ID 范围 [160*100, 180*100+100)
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
VALUES (160, @t_id, -1, '文件管理', 'system:file', 0, '', 'system-library', 0, 2, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (161, @t_id, 160, '文件中心', 'system:file:file:view', 0, 'system/file/file', 'system-reader', 0, 1, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (162, @t_id, 160, '配置管理', 'system:file:config:view', 0, 'system/file/config', 'system-settings', 0, 2, null, @u_id, NOW(), @u_id, NOW());
COMMIT;