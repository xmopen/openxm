package cn.openxm.bloguser.controller.dto;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * author Xiao Ma
 * date 2024/6/27
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserMailDTO {

    private String mail;

    /**
     * toDomainEntity 将UserMailDTO转化为邮件业务领域内的实体。
     * */
    public MailEntity toDomainEntity() {
        return MailEntity.builder().to(this.mail).type(MailEntity.MailType.MAIL_TYPE_TEXT).build();
    }

    /**
     * toUserMailDTO 将邮件领域内的实体转化为DTO传输给WEB端。
     * */
    public UserMailDTO toUserMailDTO(MailEntity mailEntity) {
        return UserMailDTO.builder().build();
    }
}
