package com.imooc.mall.from;

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
public class UserLoginFrom {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
