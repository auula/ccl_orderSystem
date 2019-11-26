package me.ponyo.order.models;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class ProductItemTest {
    @Test
    public void test01(){
        //参数 : 商品ID 商品购买数量 商品单价
        ProductItem  productItem = new ProductItem(1L,3, BigDecimal.valueOf(28.0));
        ///计算单个产品总价 测试计算产品总价
        System.out.println(productItem.computeTotal());
    }
}
