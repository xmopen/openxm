package cn.openxm.bloguser.constant;

/**
 * RedisKeysConstant Redis Keys。
 *
 * author Xiao Ma
 * date 2024/6/28
 */
public class RedisKeysConstant {

    /**
     * REDIS_KEY_USER_MAIL_CODE_KEY 用户邮件对应的Code在Redis中的保存时间。
     * */
    public static final String REDIS_KEY_USER_MAIL_CODE_KEY =  "openxm.key.user.mail.code:%s";


    /**
     * MAIL_CODE_REDIS_MINUTE_TTL 邮箱验证码缓存过期时间。
     * */
    public static final int REDIS_TTL_MAIL_CODE_REDIS_MINUTE_TTL = 15;

}
