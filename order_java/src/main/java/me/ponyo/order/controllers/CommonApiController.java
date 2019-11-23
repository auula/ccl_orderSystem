package me.ponyo.order.controllers;

import lombok.extern.slf4j.Slf4j;
import me.ponyo.order.models.BaseResult;
import me.ponyo.order.models.ProductInfo;
import me.ponyo.order.models.UserInfo;
import me.ponyo.order.services.ProductService;
import me.ponyo.order.services.UserService;
import me.ponyo.order.utils.RuleUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 公共的API 控制器
 * <p>
 * Created with IDEA. Date：2019年11月20日23:15:22
 * 本类负责公共的API请求处理工作，这里的'公共的api'是指的是 admin 和 user公共的
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */

@RestController
@RequestMapping(value = "/api_v1")
public class CommonApiController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpSession session;

    @PostMapping(value = {"/sign_up"})
    public BaseResult userSignUp(UserInfo user) {

        if (Strings.isBlank(user.getUserPassword())) {
            return new BaseResult().build(500, "密码不能空!");
        }
        if (Strings.isBlank(user.getUserAccount())) {
            return new BaseResult().build(500, "账号不能空!");
        }
        if (!RuleUtil.isNumberAccount(user.getUserAccount())) {
            return new BaseResult().build(500, "账号不符合规范!长度8位数字!");
        }
        if (!RuleUtil.isPassword(user.getUserPassword())) {
            return new BaseResult().build(500, "密码不符合规范!以字母开头，长度在6~18之间，只能包含字母、数字和下划线!");
        }
        if (userService.checkAccount(user.getUserAccount()) != null) {
            return new BaseResult().build(500, "账号已被注册!换一个试试~");
        }
        return userService.register(user) > 0 ?
                new BaseResult().build(200, "新账号:" + user.getUserAccount() + ";注册成功!")
                : new BaseResult().build(500, "注册失败!服务器忙碌请稍后重试~");
    }

    @PostMapping("/sign")
    public BaseResult userSign(UserInfo user) {

        if (Strings.isBlank(user.getUserPassword())) {
            return new BaseResult().build(500, "密码不能空!");
        }
        if (Strings.isBlank(user.getUserAccount())) {
            return new BaseResult().build(500, "账号不能空!");
        }
        if (
                userService.login(user) != null
        ) {
            session.setAttribute("u", user);
            return new BaseResult().build(200, "success");
        }
        return new BaseResult().build(500, "登录失败!请检查账号和密码!");
    }

    @GetMapping("/product")
    public BaseResult putProduct() {
        List<ProductInfo> allProductData = productService.getAllProductData();
        for (ProductInfo allProductDatum : allProductData) {
            System.out.println(allProductData.toString());
        }
        return  allProductData == null ? new BaseResult().build(500, "暂无商品数据~请稍后重试！") :
                new BaseResult().build(200,"success").add("ProductList",allProductData);
    }
}
