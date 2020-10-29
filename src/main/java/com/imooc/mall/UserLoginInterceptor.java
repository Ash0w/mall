package com.imooc.mall;

import com.imooc.mall.exception.UserLoginException;
import com.imooc.mall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.imooc.mall.consts.MallConst.CURRENT_USER;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/26
 * Time: 17:10
 * Description: Be brave or be a loser
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * true 继续执行，false中断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if (user == null) {
            log.info("user=null");
            throw new UserLoginException();
            //return false;
            //return ResposeVo.error(ResposeEnum.NEED_LOGIN);
        }
        return true;
    }
}
