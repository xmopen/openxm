package cn.openxm.bloguser.infrastructure.util.random;

import java.security.SecureRandom;

/**

 *
 * @author Xiao Ma
 * @date 2024/6/27
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class ThreadSafeRandom {

    /**
     * SECURE_RANDOM 全局唯一的随机数的实例。
     * */
    private static final SecureRandom SECURE_RANDOM_SOURCE;

    /**
     * DEFAULT_RANDOM_INT_MINI_VALUE 默认的生成的Int数值类型随机数的最小左边界。
     * */
    private static final int DEFAULT_RANDOM_INT_MINI_VALUE = 1000;

    /**
     * DEFAULT_RANDOM_INT_MAX_VALUE 默认的生成的Int数值类型的最右边界，包含当前值。
     * */
    private static final int DEFAULT_RANDOM_INT_MAX_VALUE = 9999;

    static {
        SecureRandom temp = null;
        try {
            // getInstanceStrong 其强依赖操作系统本身提供的算法能力支持，如果不存在则抛出异常。
            temp = SecureRandom.getInstanceStrong();
        }catch (Exception e) {
            // xlo.Waring(e.getMessage());
            temp = new SecureRandom();
        }
        SECURE_RANDOM_SOURCE = temp;
    }

    /**
     * 使用SecureRandom在线程安全的环境下生产具有更高安全性的随机数。
     * */
    public static int randomInt(int miniRandom, int maxiRandom) {
        if (miniRandom < 0 ) {
            miniRandom = DEFAULT_RANDOM_INT_MINI_VALUE;
        }
        if (miniRandom > maxiRandom) {
            miniRandom = DEFAULT_RANDOM_INT_MINI_VALUE;
            maxiRandom = DEFAULT_RANDOM_INT_MAX_VALUE;
        }
        return SECURE_RANDOM_SOURCE.nextInt(miniRandom,maxiRandom);
    }
}











