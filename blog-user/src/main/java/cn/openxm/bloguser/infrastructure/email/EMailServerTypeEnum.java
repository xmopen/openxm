package cn.openxm.bloguser.infrastructure.email;

import lombok.Getter;

/**
 * author Xiao Ma
 * date 2024/6/26
 */
@Getter
public enum EMailServerTypeEnum {

    /**
     * 邮件服务协议为SFTP。
     * */
    SFTP("qq"),;

    private final String emailSftpServerType;

    EMailServerTypeEnum(String emailSftpServerType){
        this.emailSftpServerType = emailSftpServerType;
    }
}
