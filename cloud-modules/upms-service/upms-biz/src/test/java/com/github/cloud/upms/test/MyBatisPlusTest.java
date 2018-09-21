package com.github.cloud.upms.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cloud.upms.biz.UpmsApplication;
import com.github.cloud.upms.biz.entity.SysUserDO;
import com.github.cloud.upms.biz.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * MyBatis-Plus-Test
 *
 * @author : czk
 * @date 2018-09-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UpmsApplication.class)
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void insert() {
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setAccount("Test");
        sysUserDO.setPhone("137");
        sysUserDO.setUserName("test");
        sysUserDO.setPwd(new BCryptPasswordEncoder().encode("123"));
        userMapper.insert(sysUserDO);
        System.out.println(sysUserDO.toString());
    }


    @Test
    public void select() {
        SysUserDO sysUserDO = userMapper.selectById(1L);
        System.out.println(sysUserDO);
        SysUserDO sysUserDO1 = userMapper.selectById(999L);
        System.out.println(sysUserDO1);
        SysUserDO sysUserDO2 = userMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getAccount, "admin"));
        System.out.println(sysUserDO2);
        List<SysUserDO> sysUserDOS = userMapper.selectList(null);
        System.out.println(sysUserDOS);
    }

    @Test
    public void update() {
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setId(3L);
        sysUserDO.setAccount("Update Test");
        sysUserDO.setPhone("157");
        userMapper.updateById(sysUserDO);
    }

    @Test
    public void delete() {
        System.out.println(userMapper.deleteById(5L));
    }


    @Test
    public void selectPage() {
        IPage<SysUserDO> page = userMapper.selectPage(new Page<>(1, 10), null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
    }


}
