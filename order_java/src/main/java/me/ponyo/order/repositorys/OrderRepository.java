package me.ponyo.order.repositorys;

import me.ponyo.order.models.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 订单数据访问层
 * <p>
 * Created with IDEA. Date：2019/12/1 4:38 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Mapper
public interface OrderRepository {
    @Options(useGeneratedKeys = true, keyProperty = "order_id")
    @Insert("INSERT INTO order_info(order_number,order_account,create_time,total_price) VALUES (#{orderNumber} ,#{orderAccount} ,#{createTime} ,#{totalPrice} )")
    int insertOrderInfo(OrderInfo orderInfo);
}
