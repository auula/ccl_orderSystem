package me.ponyo.order.Interceptors;

import lombok.extern.slf4j.Slf4j;
import me.ponyo.order.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 用户登录拦截器
 * <p>
 * Created with IDEA. Date：2019/11/23 4:21 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Slf4j
public class UserSignUpInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        UserInfo user = (UserInfo) request.getSession().getAttribute("u");
        if (user == null) {
            response.sendRedirect("/sign");
            return false;
        }
        log.info(user.toString());
        return true;

    }
}
