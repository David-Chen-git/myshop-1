package com.lang.myshop.module.user.service;

import com.lang.myshop.common.utils.IDUtils;
import com.lang.myshop.common.utils.SpringContextHolder;
import com.lang.myshop.module.user.entity.TbUser;
import com.lang.myshop.module.user.service.impl.TbUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})

public class UserServiceTest {
    @Test
    public void testLogin(){
        TbUserService tbUserService = SpringContextHolder.getBean(TbUserService.class);
        TbUser tbUser = new TbUser();
        tbUser.setUsername("root");
        tbUser.setPassword("root");


    }

    @Test
    public  void testInsertTbUser(){
        TbUser tbUser = new TbUser();
        tbUser.setId(IDUtils.genId());
        tbUser.setUsername("root");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("root".getBytes()));
        tbUser.setEmail("root@root.com");
        tbUser.setPhone("13919999999");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        TbUserService tbUserService = SpringContextHolder.getBean(TbUserService.class);
        int i = tbUserService.insertTbUser(tbUser);
        System.out.println("插入返回值 i = "+i);

    }

    @Test
    public void testPassword(){
        String password = DigestUtils.md5DigestAsHex("".getBytes());
        System.out.println(password);
    }
}
