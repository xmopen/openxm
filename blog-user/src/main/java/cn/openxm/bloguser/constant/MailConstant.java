package cn.openxm.bloguser.constant;

/**
 * 邮件常量。
 * author Xiao Ma
 * date 2024/6/27
 */
public class MailConstant {

    /**
     * 生成验证码时subject的模板。
     * */
    public static final String MAIL_GENERATE_CODE_SUBJECT_STRING_TEMPLATE = "openxm社区注册验证码，请进行查收！";

    /**
     * 生成验证码时正文内容，模板内容为HTML格式。
     * */
    public static final String MAIL_GENERATE_CODE_CONTENT_HTML_TEMPLATE = "openxm社区注册验证码: <b>%d</b>，请返回注册页面进行注册登录。";

}
