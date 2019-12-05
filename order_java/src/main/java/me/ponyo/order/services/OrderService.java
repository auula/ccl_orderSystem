package me.ponyo.order.services;

import me.ponyo.order.models.OrderInfo;

import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 用户订单服务接口
 * <p>
 *     Created with IDEA. Date：2019/11/21 11:52 上午
 *     <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public interface OrderService {

    boolean saveOrder(OrderInfo orderInfo);

    List<OrderInfo> getOrderListByUserAccount(String Account);
}
