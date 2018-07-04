package com.github.cloud.upms;

import com.github.cloud.common.dao.BaseDAO;
import com.github.cloud.upms.domain.entity.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author : czk
 * @date 2018-07-03 11:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UpmsApplication.class)
public class UpmsTest {

    @Autowired
    private BaseDAO baseDAO;

    @Test
    public void insertTest() {

        UserDO userDO = new UserDO();
        userDO.setAccount("test");
        userDO.setPwd("123");
        userDO.setActive(1);
        userDO.setGmtCreate(new Date());
        userDO.setGmtModify(new Date());
        userDO.setVersionNo(1L);
        userDO.setCreateBy(1L);
        userDO.setModifyBy(1L);
        userDO.setId(11L);
        Long id = baseDAO.insertAutoGenKey(userDO, "biz_user");
        System.out.println(id);

    }

    @Test
    public void selectTest() {
        UserDO biz_user = baseDAO.getEntity(UserDO.class, "biz_user", 1111L);
        System.out.println(biz_user);
    }
}
