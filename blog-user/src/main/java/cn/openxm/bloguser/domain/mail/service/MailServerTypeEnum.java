package cn.openxm.bloguser.domain.mail.service;

import lombok.Getter;

/**
 * MailServerTypeEnum 邮件服务协议。
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Getter
public enum MailServerTypeEnum {

    /**
     * 邮件服务协议为SFTP。
     * */
    SFTP("qq"),;

    private final String emailSftpServerType;

    MailServerTypeEnum(String emailSftpServerType){
        this.emailSftpServerType = emailSftpServerType;
    }
}
