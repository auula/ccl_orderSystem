package me.ponyo.order.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductInfo {
    private Long productId;

    private String productName;

    private String picUrl;

    private Byte productStatus;

    private BigDecimal productMoney;

    private Timestamp createTime;

    private String productDetails;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", productStatus=" + productStatus +
                ", productMoney=" + productMoney +
                ", createTime=" + createTime +
                ", productDetails='" + productDetails + '\'' +
                '}';
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(BigDecimal productMoney) {
        this.productMoney = productMoney;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails == null ? null : productDetails.trim();
    }
}