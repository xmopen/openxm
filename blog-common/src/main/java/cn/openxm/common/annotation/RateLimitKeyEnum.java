package cn.openxm.common.annotation;

/**
 * author Xiao Ma
 * date 2024/7/3
 */
public enum RateLimitKeyEnum {

    IP("ip"),MAIL_ADDRESS("mailAddress");

    RateLimitKeyEnum(String limitKey) {
    }
}
