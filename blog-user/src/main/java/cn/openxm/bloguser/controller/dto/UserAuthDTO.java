package cn.openxm.bloguser.controller.dto;

import cn.openxm.bloguser.domain.user.model.UserEntity;
import lombok.*;

/**
 * @author Xiao Ma
 * @date 2024/7/13
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

    private String mail;

    private int mailCode;

    private String nickName;

    private String password;

    private String icon;


    /**
     * toDomainEntity 将UserAuthDTO转换成DomainEntity。
     * */
    public UserEntity toDomainEntity(){
        return UserEntity.builder().mail(this.mail)
                .mailCode(this.mailCode)
                .nickName(this.nickName)
                .password(this.password)
                .build();
    }

}
