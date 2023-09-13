-- ---------------------------------------
-- orange-module-system-record 服务依赖菜单
-- sys_menu ID 范围 [120, 140)
-- sys_button ID 范围 [120*100, 140*100+100)
-- ---------------------------------------
use orange;

-- 当前时间戳
SET @ts := null;
SELECT @ts := UNIX_TIMESTAMP(NOW()) * 1000;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- 菜单和按钮数据预置
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 日志管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (120, @t_id, -1, '日志管理', 'system:record', 0, '', 'log', 'BasicLayout', 0, 2, null, @u_id, @ts, @u_id, @ts);

-- 登录日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (121, @t_id, 150, '登陆日志', 'system:record:login:view', 0, '/log/login', 'log', 'module/system/log/Login', 0, 20, null, @u_id, @ts, @u_id, @ts);

# insert into sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
# values (201, -100, @n_id, -1, '新增', 'system:department:add', 0, 0, '部门管理-新增', @u_id, @ts, @u_id, @ts),
#        (202, -100, @n_id, -1, '修改', 'system:department:edit', 0, 0, '部门管理-修改', @u_id, @ts, @u_id, @ts),
#        (203, -100, @n_id, -1, '删除', 'system:department:delete', 0, 0, '部门管理-删除', @u_id, @ts, @u_id, @ts),
#        (204, -100, @n_id, -1, '查询详情', 'system:department:get', 0, 0, '部门管理-查询详情', @u_id, @ts, @u_id, @ts),
#        (205, -100, @n_id, -1, '查询树', 'system:department:tree', 0, 0, '用户管理-查询树', @u_id, @ts, @u_id, @ts);

-- 操作日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (122, @t_id, 150, '操作日志', 'system:record:operation:view', 0, '/log/operation', 'log', 'module/system/log/Operation', 0, 18, null, @u_id, @ts, @u_id, @ts);
COMMIT;