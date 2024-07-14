package cn.openxm.bloguser.domain.mail.service.impl;

import cn.openxm.bloguser.constant.RedisKeysConstant;
import cn.openxm.bloguser.constant.RedisLuaScriptConstant;
import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.model.MailRedisDO;
import cn.openxm.bloguser.domain.mail.service.MailDomain;
import cn.openxm.common.exception.mail.NotSupportMailType;
import com.alibaba.fastjson.JSON;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Service
public class SftpMailServer implements MailDomain {

    private final JavaMailSender mailSender;

    private final RedisTemplate<String, Object> redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpMailServer.class);

    public SftpMailServer(JavaMailSender mailSender, RedisTemplate<String, Object> mailTemplate) {
        this.mailSender = mailSender;
        this.redisTemplate = mailTemplate;
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
        String jsonInfo = JSON.toJSONString(mailRedisDO);
        LOGGER.info("save mail code:[{}] mail:[{}] data:[{}]", mail.getCode(),mail.getTo(), jsonInfo);
        return Boolean.TRUE.equals(redisTemplate.execute(script,
                Collections.singletonList(this.getMailCodeKey(mail)),
                RedisKeysConstant.REDIS_KEY_USER_MAIL_CODE,
                jsonInfo,
                RedisKeysConstant.REDIS_TTL_MAIL_CODE_REDIS_SECOND_TTL));
    }

    /**
     * getMailCodeKey 获取存储邮件Code信息的Key。
     * */
    private String getMailCodeKey(MailEntity mail) {
        return String.format(RedisKeysConstant.REDIS_KEY_USER_MAIL, mail.getTo());
    }


    @Override
    public MailRedisDO getMailCodeInfo(MailEntity mail) {
        HashOperations<String, String, String> ops =  this.redisTemplate.opsForHash();
        String jsonObj =  ops.get(this.getMailCodeKey(mail),RedisKeysConstant.REDIS_KEY_USER_MAIL_CODE);
        if (jsonObj == null || jsonObj.isEmpty()) {
            return null;
        }
        LOGGER.info("get mail code info result:[{}], mail:[{}]",jsonObj,mail.getTo());
        return  JSON.parseObject(jsonObj, MailRedisDO.class);
    }
}
