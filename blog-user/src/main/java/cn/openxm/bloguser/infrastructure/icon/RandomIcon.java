package cn.openxm.bloguser.infrastructure.icon;

import cn.openxm.bloguser.infrastructure.util.ThreadSafeRandom;

/**
 * RandomIcon 获取随机Icon。
 *
 * @author Xiao Ma
 * @date 2024/7/13
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class RandomIcon {

    private static final String[] DEFAULT_ICONS = {
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/172081909815.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/172081909614.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/172081909313.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/172081909112.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/172081908811.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/172081908510.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190829.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190768.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190737.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190716.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190685.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190654.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190623.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190592.jpg",
            "https://blogs-1303903194.cos.ap-beijing.myqcloud.com/blogs/17208190561.jpg"
    };


    /**
     * getRandomIcon 获取随机Icon。
     * */
    public static String getRandomIcon() {
        return RandomIcon.DEFAULT_ICONS[ThreadSafeRandom.randomInt(0, DEFAULT_ICONS.length)];
    }

}
