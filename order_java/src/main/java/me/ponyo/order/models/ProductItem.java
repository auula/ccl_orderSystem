package me.ponyo.order.models;

import lombok.Data;

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

    private BigDecimal TotalPrice;

    private String ProductName;

    public ProductItem(Long id, Integer number,ProductInfo productInfo) {
        this.id = id;//商品id
        this.Number = number;//商品购买数量
        this.UnitPrice = productInfo.getProductMoney();//商品单价
        this.ProductName = productInfo.getProductName();//商品名字
        this.TotalPrice = this.computeTotal();//计算商品价格总和
    }

    public ProductItem(Long id, Integer number,BigDecimal money) {
        this.id = id;//商品id
        this.Number = number;//商品购买数量
        this.UnitPrice = money;//商品单价
        this.TotalPrice = this.computeTotal();//计算商品价格总和
    }

    public ProductItem(Long id, Integer number, String productName) {
        this.id = id;
        Number = number;
        ProductName = productName;
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

    public BigDecimal getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        UnitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
