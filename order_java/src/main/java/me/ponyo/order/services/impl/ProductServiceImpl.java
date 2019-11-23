package me.ponyo.order.services.impl;

import me.ponyo.order.models.ProductInfo;
import me.ponyo.order.repositorys.ProductRepository;
import me.ponyo.order.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
