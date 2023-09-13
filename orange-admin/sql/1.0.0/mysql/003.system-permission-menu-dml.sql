-- ---------------------------------------
-- orange-module-system-permission 服务依赖菜单
-- sys_menu ID 范围 [1,100)
-- sys_button ID 范围 [1*100,100*100+100)
--
-- 按钮ID范围为：[菜单ID*100, 菜单ID*100+100)
-- eg: 菜单ID：1 按钮ID：[100,200)
-- ---------------------------------------
use orange;

-- 当前时间戳
SET @ts := null;
SELECT @ts := UNIX_TIMESTAMP(NOW()) * 1000;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;


-- ----------------------------
-- 菜单和按钮数据预置
-- ----------------------------
BEGIN;
-- 系统管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (1, @t_id, -1, '系统管理', 'system:permission', 0, '', 'system', 'BasicLayout', 0, 2, null, @u_id, @ts, @u_id, @ts);

-- 部门管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (2, -100, 1, '部门管理', 'system:permission:department:view', 0, '/system/department', 'tree', 'module/system/department/Index', 0, 20, null, @u_id, @ts, @u_id, @ts);

insert into sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (200, -100, 2, -1, '新增', 'system:permission:department:add', 0, 0, '部门管理-新增', @u_id, @ts, @u_id, @ts),
       (201, -100, 2, -1, '修改', 'system:permission:department:edit', 0, 0, '部门管理-修改', @u_id, @ts, @u_id, @ts),
       (202, -100, 2, -1, '删除', 'system:permission:department:delete', 0, 0, '部门管理-删除', @u_id, @ts, @u_id, @ts),
       (203, -100, 2, -1, '查询详情', 'system:permission:department:get', 0, 0, '部门管理-查询详情', @u_id, @ts, @u_id, @ts),
       (204, -100, 2, -1, '查询树', 'system:permission:department:tree', 0, 0, '用户管理-查询树', @u_id, @ts, @u_id, @ts)
;

-- 用户管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (3, @t_id, 1, '用户管理', 'system:permission:user:page', 0, '/system/user', 'user', 'module/system/user/Index', 0, 18, null, @u_id, @ts, @u_id, @ts);

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (300, @t_id, 3, -1, '分页查询', 'system:permission:user:page', 0, 0, '用户管理-分页查询', @u_id, @ts, @u_id, @ts),
       (301, @t_id, 3, -1, '查询详情', 'system:permission:user:get', 0, 0, '用户管理-查询详情', @u_id, @ts, @u_id, @ts),
       (302, @t_id, 3, -1, '修改', 'system:permission:user:edit', 0, 0, '用户管理-根据ID更新', @u_id, @ts, @u_id, @ts),
       (303, @t_id, 3, -1, '新增', 'system:permission:user:add', 0, 0, '用户管理-新增', @u_id, @ts, @u_id, @ts),
       (304, @t_id, 3, -1, '删除', 'system:permission:user:delete', 0, 0, '用户管理-删除', @u_id, @ts, @u_id, @ts),
       (305, @t_id, 3, -1, '批量查询详情', 'system:permission:user:query-by-ids', 0, 0, '用户管理-批量查询详情', @u_id, @ts, @u_id, @ts),
       (306, @t_id, 3, -1, '更新密码', 'system:permission:user:update-password', 0, 0, '用户管理-更新密码', @u_id, @ts, @u_id, @ts)
;

-- 菜单管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (4, -100, 1, '菜单管理', 'system:permission:menu:view', 0, '/permission/menu', 'menu', 'module/system/menu/Index', 0, 2, null, @u_id, @ts, @u_id, @ts);

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (400, @t_id, 4, -1, '查询所有数据-树型结构', 'system:permission:menu:query-tree', 0, 0, '菜单管理-查询所有数据(树型结构)', @u_id, @ts, @u_id, @ts),
       (401, @t_id, 4, -1, '查询详情', 'system:permission:menu:get', 0, 0, '菜单管理-查询详情', @u_id, @ts, @u_id, @ts),
       (402, @t_id, 4, -1, '修改', 'system:permission:menu:update', 0, 0, '菜单管理-根据ID更新', @u_id, @ts, @u_id, @ts),
       (403, @t_id, 4, -1, '新增', 'system:permission:menu:add', 0, 0, '菜单管理-新增', @u_id, @ts, @u_id, @ts),
       (404, @t_id, 4, -1, '删除', 'system:permission:menu:delete', 0, 0, '菜单管理-删除', @u_id, @ts, @u_id, @ts),
       (405, @t_id, 4, -1, '查询全部菜单和按钮数据', 'system:permission:menu:query-all-menu-and-button-tree', 0, 0, '菜单管理-查询全部菜单和按钮数据', @u_id, @ts, @u_id, @ts)
;

-- 角色管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (5, -100, 1, '角色管理', 'system:permission:role:view', 0, '/permission/role', 'user', 'module/system/role/Index', 0, 12, null, @u_id, @ts, @u_id, @ts);

INSERT INTO sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (500, @t_id, 4, -1, '分页查询', 'system:permission:role:page', 0, 0, '角色管理-分页查询', @u_id, @ts, @u_id, @ts),
       (501, @t_id, 4, -1, '查询详情', 'system:permission:role:get', 0, 0, '角色管理-查询详情', @u_id, @ts, @u_id, @ts),
       (502, @t_id, 4, -1, '修改', 'system:permission:role:update', 0, 0, '角色管理-根据ID更新', @u_id, @ts, @u_id, @ts),
       (503, @t_id, 4, -1, '新增', 'system:permission:role:add', 0, 0, '角色管理-新增', @u_id, @ts, @u_id, @ts),
       (504, @t_id, 4, -1, '删除', 'system:permission:role:delete', 0, 0, '角色管理-删除', @u_id, @ts, @u_id, @ts),
       (505, @t_id, 4, -1, '查询所有数据', 'system:permission:role:query-all', 0, 0, '角色管理-根据参数查询所有数据', @u_id, @ts, @u_id, @ts)
;
COMMIT;