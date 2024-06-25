package cn.openxm.bloguser.domain.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 对应数据库中的表：t_xm_user。
 *
 * author Xiao Ma
 * date 2024/6/26
 */
@Data
@TableName(value = "t_xm_user")
@ToString
public class OpenXmUserDO implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_icon")
    private String userIcon;

    @TableField(value = "create_time")
    private Timestamp createTime;
}
