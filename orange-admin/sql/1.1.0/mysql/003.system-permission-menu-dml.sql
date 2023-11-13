-- ---------------------------------------
-- orange-module-system-permission 服务依赖菜单
-- sys_menu ID 范围 [1,100)
-- sys_button ID 范围 [1*100,100*100+100)
--
-- 按钮ID范围为：[菜单ID*100, 菜单ID*100+100)
-- eg: 菜单ID：1 按钮ID：[100,200)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- ----------------------------
-- 系统管理-菜单和按钮数据预置
-- ----------------------------
BEGIN;
-- 系统管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (1, @t_id, -1, '系统管理', 'system:permission', 0, '', 'system-settings', 0, 20, null, @u_id, NOW(), @u_id, NOW());

-- 部门管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (2, @t_id, 1, '部门管理', 'system:permission:department:view', 0, 'system/permission/department', 'system-people-circle', 0, 20, null, @u_id, NOW(), @u_id, NOW());

insert into sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (200, @t_id, 2, -1, '新增', 'system:permission:department:add', 0, 1, '部门管理-新增', @u_id, NOW(), @u_id, NOW()),
       (210, @t_id, 2, -1, '删除', 'system:permission:department:delete', 0, 10, '部门管理-删除', @u_id, NOW(), @u_id, NOW()),
       (220, @t_id, 2, -1, '修改', 'system:permission:department:edit', 0, 20, '部门管理-修改', @u_id, NOW(), @u_id, NOW())
;

-- 用户管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (3, @t_id, 1, '用户管理', 'system:permission:user:page', 0, 'system/permission/user', 'system-person-circle', 0, 30, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (300, @t_id, 3, -1, '新增', 'system:permission:user:add', 0, 1, '用户管理-新增', @u_id, NOW(), @u_id, NOW()),
       (310, @t_id, 3, -1, '删除', 'system:permission:user:delete', 0, 10, '用户管理-删除', @u_id, NOW(), @u_id, NOW()),
       (311, @t_id, 3, -1, '批量删除', 'system:permission:user:batch-delete', 0, 11, '用户管理-批量删除', @u_id, NOW(), @u_id, NOW()),
       (320, @t_id, 3, -1, '修改', 'system:permission:user:edit', 0, 20, '用户管理-根据ID更新', @u_id, NOW(), @u_id, NOW()),
       (321, @t_id, 3, -1, '修改密码', 'system:permission:user:update-password', 0, 21, '用户管理-更新密码', @u_id, NOW(), @u_id, NOW()),
       (322, @t_id, 3, -1, '重置密码', 'system:permission:user:reset-password', 0, 22, '用户管理-重置密码', @u_id, NOW(), @u_id, NOW())
;

-- 菜单管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (4, @t_id, 1, '菜单管理', 'system:permission:menu:view', 0, 'system/permission/menu', 'system-list-circle', 0, 40, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (400, @t_id, 4, -1, '新增', 'system:permission:menu:add', 0, 1, '菜单管理-新增', @u_id, NOW(), @u_id, NOW()),
       (410, @t_id, 4, -1, '删除', 'system:permission:menu:delete', 0, 10, '菜单管理-删除', @u_id, NOW(), @u_id, NOW()),
       (420, @t_id, 4, -1, '修改', 'system:permission:menu:update', 0, 20, '菜单管理-根据ID更新', @u_id, NOW(), @u_id, NOW())
;

-- 角色管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (5, @t_id, 1, '角色管理', 'system:permission:role:view', 0, 'system/permission/role', 'system-people', 0, 50, null, @u_id, NOW(), @u_id, NOW());

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (500, @t_id, 5, -1, '新增', 'system:permission:role:add', 0, 1, '角色管理-新增', @u_id, NOW(), @u_id, NOW()),
       (510, @t_id, 5, -1, '删除', 'system:permission:role:delete', 0, 10, '角色管理-删除', @u_id, NOW(), @u_id, NOW()),
       (520, @t_id, 5, -1, '修改', 'system:permission:role:update', 0, 20, '角色管理-根据ID更新', @u_id, NOW(), @u_id, NOW())
;
COMMIT;


-- ----------------------------
-- 看板-菜单
-- ----------------------------
BEGIN;
-- 角色管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (20, @t_id, -1, '首页', 'dashboard', 0, 'dashboard', 'system-home', 0, 1, null, @u_id, NOW(), @u_id, NOW());

COMMIT;


-- ----------------------------
-- 系统工具-菜单
-- ----------------------------
BEGIN;
-- 系统工具
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (30, @t_id, -1, '系统工具', 'system:tools', 0, '', 'system-apps', 0, 1000, null, @u_id, NOW(), @u_id, NOW());

-- API文档
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (31, @t_id, 30, 'API后台文档', 'system:tools:api', 0, 'http://hengzq.cn:38080/orange-admin/doc.html', 'system-document', 0, 20, null, @u_id, NOW(), @u_id, NOW());

-- 图标
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (32, @t_id, 30, '图标', 'system:tools:icon', 0, 'tools/icon', 'system-images', 0, 1, null, @u_id, NOW(), @u_id, NOW());
COMMIT;