package cn.openxm.bloguser.dao;

import cn.openxm.bloguser.BlogUserApplication;
import cn.openxm.bloguser.infrastructure.dao.UserDao;
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
    private UserDao openXmUserDao;

    @Test
    public void testDao(){
//        OpenXmUser user = this.openXmUserDao.selectById(1);
//        System.out.println(user.toString());
    }
}
