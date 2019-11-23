package me.ponyo.order.repositorys;

import me.ponyo.order.models.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ProductRepository {

    //查询使用商品信息
    @Select("SELECT * FROM product_info")
    List<ProductInfo> getAllProductLists();

}
