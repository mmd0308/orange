-- ---------------------------------------
-- orange-module-system-file 服务预置数据
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

INSERT INTO sys_file_config (id, tenant_id, storage, name, config, created_by, created_at)
VALUES (1, @t_id, 'LOCAL', '本地配置',
        '{"@class":"cn.hengzq.starter.storage.core.local.LocalStorageConfig","basePath":"/Users/hzq/workspace/private/orange","domain":"http://localhost:9000"}',
        @u_id, NOW()),
       (2, @t_id, 'ALIYUN', '阿里云测试配置',
        '{"@class":"cn.hengzq.starter.storage.core.aliyun.AliyunStorageConfig","endPoint":"http://oss-cn-beijing.aliyuncs.com","accessKeyId":"LTAI5tPbma6ekhVJZVpwE8Wi","accessKeySecret":"pcYBKV45hNgQY7kTUddBMH5yR0L6I7","bucketName":"aliyun-orange-test","domain":"https://aliyun-orange-test.oss-cn-beijing.aliyuncs.com"}',
        @u_id, NOW());



