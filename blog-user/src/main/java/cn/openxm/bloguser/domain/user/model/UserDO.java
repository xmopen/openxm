package cn.openxm.bloguser.domain.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 对应数据库中的表：t_xm_user。
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Data
@TableName(value = "t_xm_user")
@Builder
@ToString
public class UserDO implements Serializable {

    /**
     * 用户唯一标识符。
     * */
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_icon")
    private String userIcon;

    @TableField(value = "email")
    private String email;

    @TableField(value = "user_password")
    private String password;

    @TableField(value = "create_time")
    private Timestamp createTime;
}
