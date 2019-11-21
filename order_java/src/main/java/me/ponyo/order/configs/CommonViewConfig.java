package me.ponyo.order.configs;

import org.springframework.context.annotation.Configuration;
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
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sign").setViewName("sign");
        registry.addViewController("/").setViewName("sign");
        registry.addViewController("signUp").setViewName("signUp");
        registry.addViewController("/error").setViewName("error");
        super.addViewControllers(registry);
    }
}
