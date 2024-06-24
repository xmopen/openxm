package cn.openxm.common.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * author zhenxinma
 * date 2024/6/25
 */
@Data
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
