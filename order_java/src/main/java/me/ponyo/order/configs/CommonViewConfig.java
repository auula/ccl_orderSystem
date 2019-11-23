package me.ponyo.order.configs;

import me.ponyo.order.Interceptors.UserSignUpInterceptor;
import me.ponyo.order.models.UserInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 公共视图直接配置映射
 * <p>
 * Created with IDEA. Date：2019/11/21 12:05 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Configuration
public class CommonViewConfig extends WebMvcConfigurerAdapter {
    //不拦截的路径白名单
    private  final String[] userWhiteLists = {"/**", "/sign", "/signUp", "error","/static/**","/api_v1/**","/view/menus"};

    //添加用户登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSignUpInterceptor()).addPathPatterns("/**").excludePathPatterns(this.userWhiteLists);
        super.addInterceptors(registry);
    }
    //添加直接映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sign").setViewName("sign");
        registry.addViewController("/").setViewName("sign");
        registry.addViewController("signUp").setViewName("signUp");
        registry.addViewController("/error").setViewName("error");
        super.addViewControllers(registry);
    }
}
