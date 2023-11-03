-- ---------------------------------------
-- orange-module-system-permission 服务预置数据
-- ---------------------------------------

use orange;

-- 默认部门ID
SET @d_id := -100;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- 默认角色ID
SET @r_id := -100;

-- ----------------------------
-- 预置部门数据
-- ----------------------------
BEGIN;
delete
from sys_department
where id = @d_id;

INSERT INTO sys_department (id, tenant_id, name, parent_id, sort, created_by, created_at, updated_by, updated_at)
VALUES (@d_id, @t_id, '全公司', -1, 0, @u_id, NOW(), @u_id, NOW());
COMMIT;
-- ----------------------------
-- 用户信息表 清除所有的预置数据 用户默认密码 orange
-- ----------------------------
BEGIN;
delete
from sys_user
where id = @u_id;

INSERT INTO sys_user (id, tenant_id, department_id, name, email, sex, phone, username, password, remark, created_by, created_at, updated_by, updated_at)
VALUES (@u_id, @t_id, @d_id, '系统管理员', 'hengzq@yeah.net', '1', '17629990001', 'admin',
        '$2a$10$.pPlcDi1qZfD6RXRzGagdeuamQXIGRFuG83bKf9MEYiUxEwnvHj4S', '系统管理员', @u_id, NOW(), @u_id, NOW());
COMMIT;

-- ----------------------------
-- 角色预置信息
-- ----------------------------
BEGIN;
delete
from sys_role
where id = @r_id;

INSERT INTO sys_role (id, tenant_id, name, permission, sort, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (@r_id, @t_id, '系统管理员', 'admin', 1, 0, 0, '系统管理员账号-拥有所有的权限', @u_id, NOW(), @u_id, NOW());
COMMIT;

-- ----------------------------
-- 用户角色绑定
-- ----------------------------
BEGIN;
SELECT @n_id := IFNULL(MAX(id), 0) + 1
FROM sys_user_role_rl;

INSERT INTO sys_user_role_rl (id, tenant_id, user_id, role_id, created_by, created_at, updated_by, updated_at)
VALUES (@n_id, @t_id, @u_id, @r_id, @u_id, NOW(), @u_id, NOW());

COMMIT;