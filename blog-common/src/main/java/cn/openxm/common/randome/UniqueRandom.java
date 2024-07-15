package cn.openxm.common.randome;

import com.relops.snowflake.Snowflake;

import java.util.UUID;

/**
 * UniqueRandom is a thread-safe random unique identifier.
 *
 * @author Xiao Ma
 * @date 2024/7/16
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class UniqueRandom {

    /**
     * SNOW_FLAKE Snowflake instant.
     * */
    private static final Snowflake SNOW_FLAKE = new Snowflake(0);

    /**
     * nextLong the snowflake algorithm generates unique ids.
     *
     * @return the next unique id.
     * */
    public static long nextLong() {
        return UniqueRandom.SNOW_FLAKE.next();
    }


    /**
     * nextUuid next uuid.
     *
     * @return uuid of format.
     * */
    public static String nextUuid(){
        String sourceUuid =  UUID.randomUUID().toString();
        return sourceUuid.replaceAll("-", "");
    }

}
