-- ---------------------------------------
-- orange-module-system-dict  服务依赖菜单
-- sys_menu ID 范围 [100,120)
-- sys_button ID 范围 [100*100,120*100+100)
-- ---------------------------------------
use orange;

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

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset_flag, path, icon, hidden, sort, remark, created_by, created_at, updated_by, updated_at)
VALUES (100, @t_id, @s_id, '字典管理', 'system:dict:view', 0, 'system/permission/dict', 'system-library', 0, 14, null, @u_id, NOW(), @u_id, NOW());

insert into sys_button (id, tenant_id, menu_id, root_id, name, permission, preset_flag, sort, remark, created_by, created_at, updated_by, updated_at)
values  (10000, @t_id, 100, -1, '新增', 'system:dict:dict-type:add', 0, 0, '字典管理类型-新增', @u_id, NOW(), @u_id, NOW()),
       (10001, @t_id, 100, -1, '删除', 'system:dict:dict-type:delete', 0, 0, '字典管理类型-删除', @u_id, NOW(), @u_id, NOW()),
       (10002, @t_id, 100, -1, '修改', 'system:dict:dict-type:update', 0, 0, '字典管理类型-修改', @u_id, NOW(), @u_id, NOW()),
       (10010, @t_id, 100, -1, '数据-新增', 'system:dict:dict-data:add', 0, 0, '字典数据管理-新增', @u_id, NOW(), @u_id, NOW()),
       (10011, @t_id, 100, -1, '数据-修改', 'system:dict:dict-data:update', 0, 0, '字典数据管理-修改', @u_id, NOW(), @u_id, NOW()),
       (10012, @t_id, 100, -1, '数据-删除', 'system:dict:dict-data:delete', 0, 0, '字典数据管理-删除', @u_id, NOW(), @u_id, NOW())
;

COMMIT;