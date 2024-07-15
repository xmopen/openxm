package cn.openxm.randome;

import cn.openxm.common.randome.UniqueRandom;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiao Ma
 * @date 2024/7/16
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class UniqueRandomTest {

    @Test
    public void testUniqueRandom() {
        Map<String,String> map = new HashMap<String,String>();
        for (int i = 0; i < 1000000; i++) {
            String uuid = UniqueRandom.nextUuid();
            if (map.containsKey(uuid)) {
                throw new RuntimeException("contains duplicate uuids");
            }
            map.put(uuid, uuid);
        }

        System.out.println("success");
    }

}
