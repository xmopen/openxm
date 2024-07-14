package cn.openxm.bloguser.domain.mail.service;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.model.MailRedisDO;

/**
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public interface MailDomain {

    /**
     * 按照MailEntity实体进行邮件发送。
     * 注意：要按照MailEntity.MailType来进行区分时发送文本还是发送HTML类型的邮件。
     * */
    void send(MailEntity mail) throws Exception;

    /**
     * saveMailWithCodeMapping 持久化邮件和邮件验证码。
     * 接口层面必须保证一个邮箱只能在一个固定窗口请求N次。
     * */
    boolean saveMailCodeMapping(MailEntity mail) ;

    /**
     * getMailCodeInfo 获取用户邮件对应的验证码。
     * */
    MailRedisDO getMailCodeInfo(MailEntity mail);
}
