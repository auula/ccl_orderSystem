package me.ponyo.order.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 点餐主界面表示层
 * <p>
 *     Created with IDEA. Date：2019年11月20日23:15:22
 *     本类负责前端的template页面渲染响应给客户端
 *     <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Controller
@RequestMapping(value = "/view")
public class UserViewController {

    //response goods list to browser
    @GetMapping(value = {"/meus","/"})
    public String meus(){
        return "goods_list";
    }
}
