package me.ponyo.order.controllers;

import lombok.extern.slf4j.Slf4j;
import me.ponyo.order.models.ProductInfo;
import me.ponyo.order.models.ProductPicInfo;
import me.ponyo.order.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
@Slf4j
@Controller
@RequestMapping(value = "/view")
public class UserViewController {

    @Autowired
    ProductService productService;


    //response goods list to browser
    @GetMapping(value = {"/menus","/"})
    public String menus(Model ui){
        List<ProductInfo> allProductData = productService.getAllProductData();
        if (allProductData != null) {
            ui.addAttribute("Products",allProductData);
        }else{
            log.info("未获取到相关数据！");
        }
        return "goods_list";
    }

    @GetMapping(value = {"/details/{id}"})
    public String details(Model ui,@PathVariable  Long id){
        ProductInfo ProductData = productService.getOneDetailsDate(id);
        if (ProductData != null) {
            ui.addAttribute("Products",ProductData);
            //只要查到商品信息才能去查商品图片信息  以为商品id和商品图片表的id是连起来的 商品id差不多就不用去查商品图片信息了
            ui.addAttribute("p_pic",productService.getProductPicInfos(ProductData.getProductId()));
        }else{
            log.info("未获取到相关数据！");
        }
        return "details";
    }

}
