package cn.hengzq.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hengzq.orange.admin.module.system.permission.common.constants.enums.UserSexEnum;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.admin.starter.mybatis.handlers.EnumCodeTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_user")
public class UserEntity extends BaseTenantEntity {

    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    @TableField("department_id")
    private Long departmentId;


    private Long avatar;

    private String name;

    private String email;

    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private UserSexEnum sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 登陆账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

}
