package com.imooc.mall.service.impl;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void register() {
        User user=new User("xiaoyu","123456","123@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }
}