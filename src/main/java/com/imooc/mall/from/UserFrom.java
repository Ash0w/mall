package com.imooc.mall.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/22
 * Time: 0:47
 * Description: Be brave or be a loser
 */
@Data
public class UserFrom {

    //@NotBlank 用于String 会判断空格
    //@NotEmpty 用于集合
    //@NotNull
    @NotBlank/*(message = "用户名不能为空！")*/
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

}
