-- ---------------------------------------
-- orange-module-system-dict 服务预置数据
-- sys_dict_type ID 范围 [1,100)
-- sys_dict_data ID 范围 [1*100,100*100+100)
--
-- 字典数据ID：[字典类型ID*100, 字典类型ID*100+100)
-- eg: 字典类型ID：1 字典数据ID：[100,200)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--   系统数据状态-sys_common_data_status type ID范围 [1]  data ID范围 [100 - 200）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (1, @t_id, '系统数据状态', 'sys_common_data_status', 0, 0, '系统中数据状态', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (100, @t_id, 1, '正常', 'NORMAL', 'sys_common_data_status', 0, 0, '#67c23a', 0, '正常状态', @u_id, NOW(), @u_id, NOW()),
       (101, @t_id, 1, '禁用', 'DISABLE', 'sys_common_data_status', 0, 0, '#f56c6c', 0, '禁用状态', @u_id, NOW(), @u_id, NOW());
COMMIT;

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--   系统操作状态-sys_common_operation_status type ID范围 [2]  data ID范围 [200 - 300）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (2, @t_id, '系统操作状态', 'sys_common_operation_status', 0, 0, '系统中操作状态', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (200, @t_id, 1, '成功', 'SUCCESS', 'sys_common_operation_status', 0, 0, '#67c23a', 0, '操作成功状态', @u_id, NOW(), @u_id, NOW()),
       (201, @t_id, 1, '失败', 'FAIL', 'sys_common_operation_status', 0, 0, '#f56c6c', 0, '操作失败状态', @u_id, NOW(), @u_id, NOW());
COMMIT;

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--   用户性别-sys_common_user_sex type ID范围 [3]  data ID范围 [300 - 400）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (3, @t_id, '用户性别', 'sys_common_user_sex', 0, 0, '用户性别', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (300, @t_id, 1, '男', 'MALE', 'sys_common_user_sex', 0, 0, '#87d068', 0, '用户性别-男', @u_id, NOW(), @u_id, NOW()),
       (301, @t_id, 1, '女', 'FEMALE', 'sys_common_user_sex', 0, 0, '#87d068', 0, '用户性别-女', @u_id, NOW(), @u_id, NOW()),
       (302, @t_id, 1, '未知', 'UNKNOWN', 'sys_common_user_sex', 0, 0, '#83888f', 0, '用户性别-未知', @u_id, NOW(), @u_id, NOW());
COMMIT;

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--   数据预设标记-sys_common_data_preset_flag type ID范围 [4]  data ID范围 [400 - 500）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (4, @t_id, '数据预设标记', 'sys_common_data_preset_flag', 0, 0, '数据预置标志', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (400, @t_id, 1, '预置', 'PRESET', 'sys_common_data_preset_flag', 0, 0, '#909399', 0, '数据预设标记-预置', @u_id, NOW(), @u_id, NOW()),
       (401, @t_id, 1, '自定义', 'CUSTOM', 'sys_common_data_preset_flag', 0, 0, '#67c23a', 0, '数据预设标记-自定义', @u_id, NOW(), @u_id, NOW());
COMMIT;

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 请求方式  sys_common_request_method type ID范围 [5]  data ID范围 [500 - 600）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (5, @t_id, '请求方式', 'sys_common_request_method', 0, 0, '请求方式', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (500, @t_id, 1, 'GET', 'GET', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-GET', @u_id, NOW(), @u_id, NOW()),
       (501, @t_id, 1, 'HEAD', 'HEAD', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-HEAD', @u_id, NOW(), @u_id, NOW()),
       (502, @t_id, 1, 'POST', 'POST', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-POST', @u_id, NOW(), @u_id, NOW()),
       (503, @t_id, 1, 'PUT', 'PUT', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-PUT', @u_id, NOW(), @u_id, NOW()),
       (504, @t_id, 1, 'PATCH', 'PATCH', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-PATCH', @u_id, NOW(), @u_id, NOW()),
       (505, @t_id, 1, 'DELETE', 'DELETE', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-DELETE', @u_id, NOW(), @u_id, NOW()),
       (506, @t_id, 1, 'OPTIONS', 'OPTIONS', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-OPTIONS', @u_id, NOW(), @u_id, NOW()),
       (507, @t_id, 1, 'TRACE', 'TRACE', 'sys_common_request_method', 0, 0, '#87d068', 0, '请求方式-TRACE', @u_id, NOW(), @u_id, NOW());
COMMIT;



-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--   数据可见标记-sys_common_data_hidden_flag type ID范围 [6]  data ID范围 [600 - 700）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (6, @t_id, '数据可见标记', 'sys_common_data_hidden_flag', 0, 0, '数据可见标记', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (600, @t_id, 1, '隐藏', 'true', 'sys_common_data_hidden_flag', 0, 0, '#909399', 0, '数据可见标记-隐藏', @u_id, NOW(), @u_id, NOW()),
       (601, @t_id, 2, '显示', 'false', 'sys_common_data_hidden_flag', 0, 0, '#67c23a', 0, '数据可见标记-显示', @u_id, NOW(), @u_id, NOW());
COMMIT;



-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--   登录日志类型-sys_record_login_type type ID范围 [7]  data ID范围 [700 - 800）
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;
-- 新增字典类型
INSERT INTO sys_dict_type (id, tenant_id, name, dict_type, status, preset_flag, remark, created_by, created_at, updated_by, updated_at)
VALUES (7, @t_id, '登录日志类型', 'sys_record_login_type', 0, 0, '登录日志类型', @u_id, NOW(), @u_id, NOW());

-- 新增字典数据
INSERT INTO sys_dict_data (id, tenant_id, sort, dict_label, dict_value, dict_type, preset_flag, default_flag,
                           show_style, status, remark, created_by, created_at, updated_by, updated_at)
VALUES (700, @t_id, 1, '登录', 'LOGIN', 'sys_record_login_type', 0, 0, '#67c23a', 0, '登录日志类型-登录', @u_id, NOW(), @u_id, NOW()),
       (701, @t_id, 2, '登出', 'LOGOUT', 'sys_record_login_type', 0, 0, '#87d068', 0, '登录日志类型-登出', @u_id, NOW(), @u_id, NOW());
COMMIT;