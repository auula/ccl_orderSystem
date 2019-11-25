package me.ponyo.order.repositorys;

import me.ponyo.order.models.ProductInfo;
import me.ponyo.order.models.ProductPicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ProductRepository {

    //查询使用商品信息
    @Select("SELECT * FROM product_info")
    List<ProductInfo> getAllProductLists();
    //通过id查询商品信息
    @Select("SELECT * FROM product_info WHERE product_id = #{id} ")
    ProductInfo findProductInfoById(Long id);

    //通过id查询商品pic信息
    @Select("SELECT * FROM order_sys.product_pic_info WHERE  product_id = #{productId} ")
    List<ProductPicInfo> findProductPicInfoByProductId(long productId);
}
