package com.lang.myshop.module.user.mapper;

import com.lang.myshop.module.user.entity.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class UserMapperTest {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setId(4L);
        tbUser.setUsername("root");
        tbUser.setPassword("root");
        tbUser.setEmail("admin@admin.com");
        tbUser.setPhone("139999999999");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserMapper.insert(tbUser);
    }
    @Test
    public void  testSelect(){
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(1L);
        System.out.println("username="+tbUser.getUsername());
    }
}
