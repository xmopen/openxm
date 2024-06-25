package cn.openxm.bloguser.dao;

import cn.openxm.bloguser.BlogUserApplication;
import cn.openxm.bloguser.infrastructure.dao.OpenXmUserDao;
import cn.openxm.common.pojo.po.OpenXmUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * author Xiao Ma
 * date 2024/6/25
 */
@SpringBootTest(classes = {BlogUserApplication.class})
public class OpenXmUserDaoTest {

    @Autowired
    private OpenXmUserDao openXmUserDao;

    @Test
    public void testDao(){
//        OpenXmUser user = this.openXmUserDao.selectById(1);
//        System.out.println(user.toString());
    }
}
