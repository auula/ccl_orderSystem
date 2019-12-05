package me.ponyo.order.services.impl;

import me.ponyo.order.models.OrderInfo;
import me.ponyo.order.repositorys.OrderRepository;
import me.ponyo.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 订单访问层
 * <p>
 * Created with IDEA. Date：2019/12/1 4:36 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderInfo> getOrderListByUserAccount(String Account) {
        return orderRepository.findOrderListByUserAccount(Account);
    }

    @Override
    public boolean saveOrder(OrderInfo orderInfo) {
        return orderRepository.insertOrderInfo(orderInfo) > 0 ? true : false ;
    }
}
