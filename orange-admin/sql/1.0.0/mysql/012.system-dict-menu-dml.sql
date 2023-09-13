-- ---------------------------------------
-- orange-module-system-dict  服务依赖菜单
-- sys_menu ID 范围 [100,120)
-- sys_button ID 范围 [100*100,120*100+100)
-- ---------------------------------------
use orange;

-- 当前时间戳
SET @ts := null;
SELECT @ts := UNIX_TIMESTAMP(NOW()) * 1000;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- 系统管理菜单ID
SET @s_id := 1;

-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- 字典管理 挂在系统管理菜单下面
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, component, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (100, @t_id, @s_id, '字典管理', 'system:dict:view', 0, '/basic/dict', 'user', 'module/system/dict/Index', 0, 14, null, @u_id, @ts, @u_id, @ts);

insert into sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values (10000, @t_id, 100, -1, '分页查询', 'system:dict:dict-type:page', 0, 0, '字典管理类型-分页查询', @u_id, @ts, @u_id, @ts),
       (10001, @t_id, 100, -1, '新增', 'system:dict:dict-type:add', 0, 0, '字典管理类型-新增', @u_id, @ts, @u_id, @ts),
       (10002, @t_id, 100, -1, '删除', 'system:dict:dict-type:delete', 0, 0, '字典管理类型-删除', @u_id, @ts, @u_id, @ts),
       (10003, @t_id, 100, -1, '查询详情', 'system:dict:dict-type:get', 0, 0, '字典管理类型-查询详情', @u_id, @ts, @u_id, @ts),
       (10004, @t_id, 100, -1, '修改', 'system:dict:dict-type:update', 0, 0, '字典管理类型-修改', @u_id, @ts, @u_id, @ts),
       (10005, @t_id, 100, -1, '数据-新增', 'system:dict:dict-data:add', 0, 0, '字典数据管理-新增', @u_id, @ts, @u_id, @ts),
       (10006, @t_id, 100, -1, '数据-修改', 'system:dict:dict-data:update', 0, 0, '字典数据管理-修改', @u_id, @ts, @u_id, @ts),
       (10007, @t_id, 100, -1, '数据-删除', 'system:dict:dict-data:delete', 0, 0, '字典数据管理-删除', @u_id, @ts, @u_id, @ts),
       (10008, @t_id, 100, -1, '数据-查询详情', 'system:dict:dict-data:get', 0, 0, '字典数据管理-查询详情', @u_id, @ts, @u_id, @ts),
       (10009, @t_id, 100, -1, '数据-分页查询', 'system:dict:dict-data:page', 0, 0, '字典数据管理-分页查询', @u_id, @ts, @u_id, @ts),
       (10010, @t_id, 100, -1, '数据-查询所有数据', 'system:dict:dict-data:query-all', 0, 0, '字典数据管理-查询所有数据', @u_id, @ts, @u_id, @ts),
       (10011, @t_id, 100, -1, '数据-根据字典类型获取数据', 'system:dict:dict-data:query-by-type', 0, 0, '字典数据管理-根据字典类型获取数据', @u_id, @ts, @u_id, @ts)
;

COMMIT;