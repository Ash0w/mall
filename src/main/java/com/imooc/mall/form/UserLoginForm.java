package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/22
 * Time: 0:47
 * Description: Be brave or be a loser
 */
@Data
public class UserLoginForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
