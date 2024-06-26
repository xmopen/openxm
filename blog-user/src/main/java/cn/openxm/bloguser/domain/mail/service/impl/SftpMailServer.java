package cn.openxm.bloguser.domain.mail.service.impl;

import cn.openxm.bloguser.constant.RedisKeysConstant;
import cn.openxm.bloguser.constant.RedisLuaScriptConstant;
import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.model.MailRedisDO;
import cn.openxm.bloguser.domain.mail.service.Mail;
import cn.openxm.common.exception.mail.NotSupportMailType;
import jakarta.mail.internet.MimeMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        switch (mail.getType()) {
            case MAIL_TYPE_HTML -> this.sendHtml(mail);
            case MAIL_TYPE_TEXT -> this.sendText(mail);
            default -> throw new NotSupportMailType(String.format("Not support the mail type: %s, expect is (0,1)",
                    mail.getType()));
        }
    }

    /**
     * saveMailCodeMapping 如果重复发送邮件，即便过了上层的限制，这里只需要覆盖即可，而覆盖则会将原先的Code失效。并重新启用新的Code。
     * */
    @Override
    public boolean saveMailCodeMapping(MailEntity mail) {
        MailRedisDO mailRedisDO = MailRedisDO.builder().mail(mail.getTo()).code(mail.getCode()).count(1).build();
        DefaultRedisScript<Boolean> script =  new DefaultRedisScript<>();
        script.setScriptText(RedisLuaScriptConstant.REDIS_LUA_SCRIPT_HASH_SET_WITH_EXPIRED);
        script.setResultType(Boolean.class);
        return Boolean.TRUE.equals(redisTemplate.execute(script,
                Collections.singletonList(String.format(RedisKeysConstant.REDIS_KEY_USER_MAIL, mail.getTo())),
                RedisKeysConstant.REDIS_KEY_USER_MAIL_CODE, mailRedisDO,
                RedisKeysConstant.REDIS_TTL_MAIL_CODE_REDIS_SECOND_TTL));
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
