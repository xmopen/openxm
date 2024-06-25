package cn.openxm.bloguser.domain.mail.service;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import org.springframework.stereotype.Component;

/**
 * author Xiao Ma
 * date 2024/6/26
 */
public interface Mail {
    void sendText(MailEntity mail) throws Exception;

    void sendHtml(MailEntity mail) throws Exception;
}
