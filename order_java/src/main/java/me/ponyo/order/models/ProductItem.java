package me.ponyo.order.models;

import java.math.BigDecimal;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 商品个体
 * <p>
 * Created with IDEA. Date：2019/11/26 6:38 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public class ProductItem {
    private Long id;
    private Integer Number;
    private BigDecimal UnitPrice;

    public BigDecimal getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(BigDecimal money) {
        UnitPrice = money;
    }

    private BigDecimal TotalPrice;


    public ProductItem(Long id, Integer number,BigDecimal money) {
        this.id = id;
        this.Number = number;
        this.UnitPrice = money;
        this.TotalPrice = this.computeTotal();
    }

    //计算单个产品总价
    public BigDecimal computeTotal() {
        //购买数量乘以单价
        return (new BigDecimal(this.Number).multiply(this.UnitPrice));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        TotalPrice = totalPrice;
    }

}
