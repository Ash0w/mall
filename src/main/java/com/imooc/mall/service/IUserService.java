package com.imooc.mall.service;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/21
 * Time: 11:56
 * Description: Be brave or be a loser
 */
public interface IUserService {
    /**
     * 注册
     *
     * @param user the user
     * @return the response vo
     */
    ResponseVo<User> register(User user);

    /**
     * 登录 response vo.
     *
     * @param username the username
     * @param password the password
     * @return the response vo
     */
    ResponseVo<User> login(String username, String password);

}
