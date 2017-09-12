package com.aoshop.vo;

import java.math.BigDecimal;

/**
 * author:liuao
 * description:
 * Date: create on 21:19 2017/9/6
 * modify by:
 */
public class CartProductVo {
    //结合了产品和购物车的抽象对象
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private String productName;
    private String productSubTitle;

    private String productMainImage;
    private BigDecimal productPrice;
    private BigDecimal productTotalPrice;
    private Integer productStock;

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    private Integer productStatus;
    private Integer productChecked;

    private String limitQuantity;//限制数量的返回结果

    @Override
    public String toString() {
        return "CartProductVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", productSubTitle='" + productSubTitle + '\'' +
                ", productMainImage='" + productMainImage + '\'' +
                ", productPrice=" + productPrice +
                ", productTotalPrice=" + productTotalPrice +
                ", productStock=" + productStock +
                ", productChecked=" + productChecked +
                ", limitQuantity='" + limitQuantity + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubTitle() {
        return productSubTitle;
    }

    public void setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductChecked() {
        return productChecked;
    }

    public void setProductChecked(Integer productChecked) {
        this.productChecked = productChecked;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }


}
