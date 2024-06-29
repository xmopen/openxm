package cn.openxm.bloguser.domain.mail.service;

import cn.openxm.bloguser.domain.mail.model.MailEntity;

/**
 * author Xiao Ma
 * date 2024/6/26
 */
public interface Mail {

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
}
