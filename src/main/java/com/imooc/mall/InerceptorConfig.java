package com.imooc.mall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/29
 * Time: 10:58
 * Description: Be brave or be a loser
 *
 * @author xiaoyu
 */
@Configuration
public class InerceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/error","/carts","/user/register", "/user/login", "/categories", "/products", "/products/*");
    }
}
