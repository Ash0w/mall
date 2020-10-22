package com.imooc.mall.controller;

import com.imooc.mall.from.UserFrom;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static com.imooc.mall.enums.ResposeEnum.PARAM_ERROR;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/21
 * Time: 16:43
 * Description: Be brave or be a loser
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseVo register(@Valid @RequestBody UserFrom userFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误，{}{}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }
        User user=new User();
        BeanUtils.copyProperties(userFrom,user);
        return userService.register(user);
    }
}
