package cn.openxm.bloguser.application.manager;

import cn.openxm.bloguser.constant.MailConstant;
import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.service.Mail;
import cn.openxm.response.Response;
import cn.openxm.response.ResponseEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * author Xiao Ma
 * date 2024/6/27
 */
@Service
public class UserMailManager {

    /********************************静态常量*********************************/
    @Value("${email.user.from.addr}")
    private String mailFromUserAddr;

    // 注入邮件领域服务。
    private final Mail mailService;

    public UserMailManager(Mail mailService) {
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

        try {
            this.mailService.saveMailCodeMapping(mailEntity);
            this.mailService.send(mailEntity);
        }catch (Exception e){
            // TODO: 日志信息处理。
            return Response.fail(ResponseEnum.RESPONSE_FAIL_SYSTEM_ERROR);
        }

        return Response.success("");
    }
}
