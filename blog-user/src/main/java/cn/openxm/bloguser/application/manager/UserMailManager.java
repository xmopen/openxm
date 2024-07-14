package cn.openxm.bloguser.application.manager;

import cn.openxm.bloguser.constant.MailConstant;
import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.service.MailDomain;
import cn.openxm.common.response.Response;
import cn.openxm.common.response.ResponseEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * UserMailManager 用户邮件管理中心。
 * @author Xiao Ma
 * @date 2024/6/27
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Service
public class UserMailManager {

    @Value("${email.user.from.addr}")
    private String mailFromUserAddr;

    private final MailDomain mailService;

    public UserMailManager(MailDomain mailService) {
        this.mailService = mailService;
    }

    /**
     * generateMailCode 组合不同领域服务来实现业务研发，这里只是功能组合，不做任务业务研发。
     * Service和Entity之间的关系：其他领域模型调用当前了领域功能时可以使用Service。
     * */
    public Response<String> generateMailCode(MailEntity mailEntity){
        if (mailEntity == null || Objects.equals(mailEntity.getTo(), "")) {
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_MAIL_IS_EMPTY);
        }

        // 1、针对MailEntity进行业务逻辑数据填充。
        mailEntity.setFrom(this.mailFromUserAddr);
        mailEntity.setSubject(String.format(MailConstant.MAIL_GENERATE_CODE_SUBJECT_STRING_TEMPLATE));
        mailEntity.setContent(String.format(MailConstant.MAIL_GENERATE_CODE_CONTENT_HTML_TEMPLATE,
                mailEntity.generateMailCode()));

        // 2、持久化Code到Redis中。
        try {
            if (!this.mailService.saveMailCodeMapping(mailEntity)) {
                return Response.fail(ResponseEnum.RESPONSE_FAIL_SYSTEM_ERROR);
            }
            // 3、发送邮件。
            this.mailService.send(mailEntity);
        }catch (Exception e){
            return Response.fail(ResponseEnum.RESPONSE_FAIL_SYSTEM_ERROR);
        }

        return Response.success("");
    }
}
