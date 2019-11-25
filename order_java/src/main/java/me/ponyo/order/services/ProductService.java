package me.ponyo.order.services;

import me.ponyo.order.models.ProductInfo;
import me.ponyo.order.models.ProductPicInfo;

import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 商品服务接口
 * <p>
 * Created with IDEA. Date：2019/11/21 11:54 上午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public interface ProductService {

    //获取所有商品信息
    List<ProductInfo> getAllProductData();

    ProductInfo getOneDetailsDate(Long id);

    List<ProductPicInfo> getProductPicInfos(Long productId);
}
