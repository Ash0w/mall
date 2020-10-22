package com.imooc.mall.service;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;

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
     * @param user
     */
    ResponseVo register(User user);

}
