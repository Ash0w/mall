package com.imooc.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.mall.enums.ResposeEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/21
 * Time: 18:31
 * Description: Be brave or be a loser
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseVo<T> successByMsg(String msg) {
        return new ResponseVo<>(ResposeEnum.SUCCESS.getCode(), msg);
    }
    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(ResposeEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(ResposeEnum.SUCCESS.getCode(), ResposeEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseVo<T> error(ResposeEnum resposeEnum) {
        return new ResponseVo<>(resposeEnum.getCode(), resposeEnum.getDesc());
    }
    public static <T> ResponseVo<T> error(ResposeEnum resposeEnum,String msg) {
        return new ResponseVo<>(resposeEnum.getCode(), msg);
    }
    public static <T> ResponseVo<T> error(ResposeEnum resposeEnum, BindingResult bindingResult) {
        return new ResponseVo<>(resposeEnum.getCode(), Objects.requireNonNull(bindingResult.getFieldError()).getField()+""+bindingResult.getFieldError().getDefaultMessage());
    }
}
