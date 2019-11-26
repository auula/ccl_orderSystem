package me.ponyo.order.services.impl;

import me.ponyo.order.models.ProductInfo;
import me.ponyo.order.models.ProductPicInfo;
import me.ponyo.order.repositorys.ProductRepository;
import me.ponyo.order.services.ProductService;
import me.ponyo.order.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 程序主启动类
 * <p>
 * Created with IDEA. Date：2019/11/23 9:08 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductInfo> getAllProductData() {

        return productRepository.getAllProductLists();
    }

    @Override
    public BigDecimal getUnitPrice(Long id) {
        if (id == null){
            return BigDecimal.valueOf(0);
        }
        return productRepository.findProductMoneyByProductId(id);
    }

    @Override
    public ProductInfo getOneDetailsDate(Long id) {
        //防止为空
        if (id == null) {
            id = 1L;
        }
        ProductInfo productInfo = productRepository.findProductInfoById(id);
        //无数据时就设置空数据 or 其实这里可以直接返回null的  但是这样不友好 毕竟我们使用的是template展示 不是Json展示
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setProductId(1L);
            productInfo.setProductName("无数据！");
            productInfo.setProductMoney(BigDecimal.valueOf(0.0));
            productInfo.setPicUrl("http://hbimg.b0.upaiyun.com/bdaca9a07e1a8947c00c2f826ebf848750927aa24963-cATwbg_fw658");
            productInfo.setProductDetails("暂无数据,换一个菜单试试！");
            productInfo.setProductStatus(Byte.parseByte("0"));
            productInfo.setCreateTime(DateUtil.asDateToTimestamp());
        }
        return productInfo;
    }

    @Override
    public List<ProductPicInfo> getProductPicInfos(Long productId) {
        if (productId == null) {
            productId = 1L;
        }
        return productRepository.findProductPicInfoByProductId(productId);
    }
}
