package com.aoshop.util;

import java.math.BigDecimal;

/**
 * author:liuao
 * description:
 * Date: create on 20:46 2017/9/6
 * modify by:
 */
public class BIgDecimalUtil {
    private BIgDecimalUtil(){

    }
    public static BigDecimal add(double b1,double b2){
        BigDecimal b3= new BigDecimal(Double.toString(b1));
        BigDecimal b4 = new BigDecimal(Double.toString(b2));
        return b3.add(b4);
    }

    public static BigDecimal sub(double b1,double b2){
        BigDecimal b3= new BigDecimal(Double.toString(b1));
        BigDecimal b4 = new BigDecimal(Double.toString(b2));
        return b3.subtract(b4);
    }

    public static BigDecimal mul(double b1,double b2){
        BigDecimal b3= new BigDecimal(Double.toString(b1));
        BigDecimal b4 = new BigDecimal(Double.toString(b2));
        return b3.multiply(b4);
    }

    public static BigDecimal div(double b1,double b2){
        BigDecimal b3= new BigDecimal(Double.toString(b1));
        BigDecimal b4 = new BigDecimal(Double.toString(b2));
        return b3.divide(b4,2,BigDecimal.ROUND_HALF_UP);
    }

}
