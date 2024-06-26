package cn.openxm.bloguser.application.manager;

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
     * */
    public Response<String> generateMailCode(MailEntity mailEntity){
        if (mailEntity == null || Objects.equals(mailEntity.getTo(), "")) {
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_MAIL_IS_EMPTY);
        }
        mailEntity.setFrom(this.mailFromUserAddr);
        mailEntity.setSubject("");
        mailEntity.setContent("");

        // TODO: Service和Entity之间的关系是什么：其他领域模型调用当前了领域功能时可以使用Service。现在用不上。
        // 1、生成随机验证码

        // 2、短暂进行持久化验证码按照mail:code的形式


        try {
            // 3、发送。
            this.mailService.sendText(mailEntity);
        }catch (Exception e){
            return Response.fail(ResponseEnum.RESPONSE_FAIL_SYSTEM_ERROR);
        }

        return Response.success("");
    }
}
