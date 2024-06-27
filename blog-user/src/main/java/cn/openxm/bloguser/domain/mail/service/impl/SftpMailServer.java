package cn.openxm.bloguser.domain.mail.service.impl;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.model.MailRedisDO;
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

    // TODO: 暂时存在问题。
    @Resource
    private JavaMailSender mailSender;

    @Override
    public void send(MailEntity mail) throws Exception {

    }

    /**
     * 用什么数据结构比较合适？
     * 为了更加节省内存，可以使用pb进行编解码。
     * */
    @Override
    public void saveMailCodeMapping(MailEntity mail) throws Exception {
        // 1、先确定是否存在。
        // 1、保存映射关系。
        MailRedisDO mailRedisDO =  MailRedisDO.builder().mail(mail.getTo()).code(mail.getCode()).count(1).build();
        // Redis存储。
    }
}
