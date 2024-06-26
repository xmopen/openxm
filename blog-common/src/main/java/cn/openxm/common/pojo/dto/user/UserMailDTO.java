package cn.openxm.common.pojo.dto.user;

import lombok.*;

import java.io.Serializable;

/**
 * author Xiao Ma
 * date 2024/6/27
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserMailDTO implements Serializable {
    private String mail;
}
