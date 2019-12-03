package me.ponyo.order.controllers;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import me.ponyo.order.models.*;
import me.ponyo.order.services.OrderService;
import me.ponyo.order.services.ProductService;
import me.ponyo.order.services.UserService;
import me.ponyo.order.utils.EncryptionUtil;
import me.ponyo.order.utils.JsonUtil;
import me.ponyo.order.utils.RuleUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
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
    HttpServletResponse response;

    @Autowired
    OrderService orderService;

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
    public BaseResult getProducts() {
        List<ProductInfo> allProductData = productService.getAllProductData();
//        for (ProductInfo allProductDatum : allProductData) {
//            log.info(allProductData.toString());
//        }
        return allProductData == null ? new BaseResult().build(500, "暂无商品数据~请稍后重试！") :
                new BaseResult().build(200, "success").add("ProductList", allProductData);
    }


    @PostMapping("/shop_cart/list")
    public BaseResult shopCart(String id, String number) {
        if (Strings.isBlank(id) || Strings.isBlank(number)) {
            return new BaseResult().build(500, "购物车参数传递失败!");
        }
        Map<Long, ProductItem> shopCart = getProductItemMap(Long.valueOf(id), Integer.valueOf(number));
        if (number.equals("0")) {
            //数量等于0的时候删除商品
            shopCart.remove(Long.valueOf(id));
            //这里当购物车map空的时候设置为空 原因是:防止前端页面当购物车没有数据还显示合计和结算
            if (shopCart.size() == 0) {
                shopCart = null;
                session.setAttribute("_shop", shopCart);
                return new BaseResult().build(200, "暂无商品数据~请稍后重试！");
            }
        }
        BigDecimal totalMoney = getShopCartTotalMoney(shopCart);
        session.setAttribute("totalMoney", totalMoney);
        return new BaseResult().build(200, "success").add("shopCartList", shopCart).add("totalMoney", totalMoney);
    }

    @GetMapping("/shop_cart/lists")
    public BaseResult shopCartList() {
        Map<Long, ProductItem> shopCart = (Map<Long, ProductItem>) session.getAttribute("_shop");
        if (shopCart == null) {
            return new BaseResult().build(400, " <i class=\"icon-shopcart-outline\"></i>你的购物车空空如也");
        }
        BigDecimal totalMoney = getShopCartTotalMoney(shopCart);
        return new BaseResult().build(200, "success").add("shopCartList", shopCart).add("totalMoney", totalMoney);
    }

    private BigDecimal getShopCartTotalMoney(Map<Long, ProductItem> shopCart) {
        //计算购物车的总价 所有商品的总价
        BigDecimal totalMoney = new BigDecimal(0.0);
        for (Map.Entry<Long, ProductItem> ProductItemEntry : shopCart.entrySet()) {
            /* 下面注释这样写是不对的 大坑  然后Google一下才明白
             * 文章链接:https://blog.csdn.net/baidu_37107022/article/details/78632316
             * totalMoney.add(ProductItemEntry.getValue().getTotalPrice());
             */
            totalMoney = totalMoney.add(ProductItemEntry.getValue().getTotalPrice());
        }
        return totalMoney;
    }

    private Map<Long, ProductItem> getProductItemMap(Long id, Integer number) {
        //获取存储在session中的购物车
        Map<Long, ProductItem> shopCart = (Map<Long, ProductItem>) session.getAttribute("_shop");
        if (shopCart == null) {
            //如果没有就直接创建一个
            session.setAttribute("_shop", new LinkedHashMap<>());
        }
        shopCart = (Map<Long, ProductItem>) session.getAttribute("_shop");//从session中拿去购物车数据
        ProductInfo productInfo = productService.getProductInfo(id);
        //他会自动去重的
        if (productInfo != null) { //这里判断商品是否为空  防止用户传过来的商品id是空的
            shopCart.put(id, new ProductItem(id, number, productInfo));
        }
        session.setAttribute("_shop", shopCart);
        return shopCart;
    }

    //结算
    @PostMapping(value = "/checkOut")
    public BaseResult checkOut() {
        Map<Long, ProductItem> shopCart = (Map<Long, ProductItem>) session.getAttribute("_shop");
        if (shopCart == null) {
            return new BaseResult().build(400, "你的购物车空空如也~");
        }
        //生成订单id
        String randomStrUUID = EncryptionUtil.randomStrUUID();
        boolean b = orderService.saveOrder(new OrderInfo(randomStrUUID, "admin", (BigDecimal) session.getAttribute("totalMoney")));
        return b ? new BaseResult().build(200, "下单成功!").add("order_code", randomStrUUID) : new BaseResult().build(500, "系统忙碌稍后重试~");
    }

    @GetMapping("/asQrCode")
    public void asQrCode(@RequestParam String text) {
        try {
            String bg = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575379926054&di=26b026a7ab7687e7b0902673f683e964&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F7f9654674a2ef56a92b4e5813620312bc9c6330410c34-BGM4d0_fw658";
            BufferedImage bufferedImage = QrCodeGenWrapper.of(text)
                    .setBgImg(bg)
                    .setW(500)
                    .setBgOpacity(0.5f).asBufferedImage();
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
            JsonUtil.outJson(response, new BaseResult().build(500, "生成二维码异常!"));
        } catch (WriterException e) {
            log.error(e.getMessage());
            JsonUtil.outJson(response, new BaseResult().build(500, "二维码写出异常!"));
        }
    }

//    @GetMapping("/add")
//    public BaseResult addShopCart(Long id, Integer count) {
//        //获取存储在session中的购物车
//        Map<Long, Integer> shopCartlist = (Map<Long, Integer>) session.getAttribute("_shop");
//        if (shopCartlist == null) {
//            //如果没有就直接创建一个
//            session.setAttribute("_shop", new LinkedHashMap<>());
//        }
//        //从session中拿去购物车数据
//        shopCartlist = (Map<Long, Integer>) session.getAttribute("_shop");
//        //他会自动去重的
//        shopCartlist.put(id, count);
//        session.setAttribute("_shop", shopCartlist);
//        return new BaseResult().build(200, "success").add("shopCartList", shopCartlist);
//    }


}
