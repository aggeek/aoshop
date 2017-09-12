package com.aoshop.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * author:liuao
 * description:
 * Date: create on 22:41 2017/9/6
 * modify by:
 */
public class CartVo {

    private List<CartProductVo> list;

    private BigDecimal cartTotalPrice;

    private boolean allChecked;

    private String imageHost;

    public List<CartProductVo> getList() {
        return list;
    }

    public void setList(List<CartProductVo> list) {
        this.list = list;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public boolean isAllChecked() {
        return allChecked;
    }

    public void setAllChecked(boolean allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "list=" + list +
                ", cartTotalPrice=" + cartTotalPrice +
                ", allChecked=" + allChecked +
                ", imageHost='" + imageHost + '\'' +
                '}';
    }
}
