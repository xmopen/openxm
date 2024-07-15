package cn.openxm.bloguser.constant;

/**
 * RedisKeysConstant Redis Keys。
 *
 * @author Xiao Ma
 * @date 2024/6/28
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class RedisKeysConstant {

    /**
     * REDIS_KEY_USER_MAIL_CODE_KEY user email verification code key.
     * */
    public static final String REDIS_KEY_USER_MAIL =  "openxm.key.user.mail:%s";

    /**
     * REDIS_KEY_USER_MAIL_CODE is secondary key of the REDIS_KEY_USER_MAIL.
     * */
    public static final String REDIS_KEY_USER_MAIL_CODE = "code";

    /**
     * REDIS_TTL_MAIL_CODE_REDIS_SECOND_TTL expiration time of the email verification code cache.
     * */
    public static final int REDIS_TTL_MAIL_CODE_REDIS_SECOND_TTL = 600;

    /**
     * REDIS_KEY_UNIQUE_USER_INFO_TOKEN the unique key of user information in redis.
     * */
    public static final String REDIS_KEY_UNIQUE_USER_INFO_TOKEN =  "openxm.key.unique.user.info:%s";

}
