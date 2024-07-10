package cn.openxm.bloguser.domain.mail.model;

import lombok.*;

/**
 * @author Xiao Ma
 * @date 2024/6/27
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailRedisDO {
    /**
     * 邮件地址。
     * */
    private String mail;

    /**
     * 邮件地址当前对应的有效的验证码。
     * */
    private Integer code;

    /**
     * 当前邮件在业务时间窗口范围内执行的次数。
     * */
    private int count;
}
