package cn.openxm.bloguser.domain.mail.service.impl;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.model.MailRedisDO;
import cn.openxm.bloguser.domain.mail.service.Mail;
import cn.openxm.common.exception.mail.NotSupportMailType;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * author Xiao Ma
 * date 2024/6/26
 */
@Service
public class SftpMailServer implements Mail {

    private final JavaMailSender mailSender;

    private final RedisTemplate<String, Object> redisTemplate;

    public SftpMailServer(JavaMailSender mailSender, RedisTemplate<String, Object> mailTemplate) {
        this.mailSender = mailSender;
        this.redisTemplate = mailTemplate;
    }

    @Override
    public void send(MailEntity mail) throws Exception {

    }

    /**
     * 用什么数据结构比较合适？
     * 为了更加节省内存，可以使用pb进行编解码。
     */
    @Override
    public void saveMailCodeMapping(MailEntity mail) throws Exception {
        // 1、先确定是否存在。
        // 1、保存映射关系。
        MailRedisDO mailRedisDO = MailRedisDO.builder().mail(mail.getTo()).code(mail.getCode()).count(1).build();
        // Redis存储。
        redisTemplate.opsForHash().put(mail.getTo(), "", mailRedisDO);
        // TODO: 待处理。
        // 不是原子操作？
        //  redisTemplate.expire();
        this.sendMail(mail);
    }


    private void sendMail(MailEntity mail) throws Exception {
        // switch 表达式可以拥有返回值
        switch (mail.getType()) {
            case MAIL_TYPE_HTML -> this.sendHtml(mail);
            case MAIL_TYPE_TEXT -> this.sendText(mail);
            default -> throw new NotSupportMailType(String.format("Not support the mail type: %s, expect is (0,1)",
                    mail.getType()));
        }
    }

    private void sendText(MailEntity mail) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        this.mailSender.send(message);
    }

    private void sendHtml(MailEntity mail) throws Exception {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent(), true);
        mailSender.send(message);
    }
}
