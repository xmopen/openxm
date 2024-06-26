package cn.openxm.common.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * author Xiao Ma
 * date 2024/6/25
 */
@Data
@TableName(value = "t_xm_user")
public class OpenXmUser implements Serializable {

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
