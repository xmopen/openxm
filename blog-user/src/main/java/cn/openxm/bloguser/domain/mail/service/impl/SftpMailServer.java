package cn.openxm.bloguser.domain.mail.service.impl;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.service.Mail;
import jakarta.annotation.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * author Xiao Ma
 * date 2024/6/26
 */
@Service
public class SftpMailServer implements Mail {

    @Resource
    private JavaMailSender mailSender;

    @Override
    public void sendText(MailEntity mail) throws Exception {
    }

    @Override
    public void sendHtml(MailEntity mail) throws Exception {

    }
}
