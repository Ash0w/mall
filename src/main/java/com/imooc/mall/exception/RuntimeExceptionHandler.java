package com.imooc.mall.exception;

import com.imooc.mall.enums.ResposeEnum;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.imooc.mall.enums.ResposeEnum.ERROR;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/22
 * Time: 10:55
 * Description: Be brave or be a loser
 */
@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    //@ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseVo hander(RuntimeException e) {
        return ResponseVo.error(ERROR, e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginHandle() {
        return ResponseVo.error(ResposeEnum.NEED_LOGIN);
    }

    /**
     * 统一表单验证处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo notValidExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Objects.requireNonNull(bindingResult.getFieldError());
        return ResponseVo.error(ResposeEnum.PARAM_ERROR,
                bindingResult.getFieldError().getField() + "" + bindingResult.getFieldError().getDefaultMessage());
    }
}
