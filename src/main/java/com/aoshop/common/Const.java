package com.aoshop.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * author:liuao
 * description:
 * Date: create on 23:09 2017/8/16
 * modify by:
 */
public class Const {

    public static  final  String CURRENT_USER="CURRENT_USER";

    public static  final  String EMAIL="email";

    public static  final  String USERNAME="username";
    public interface role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }


    public interface ProductListOrderBy{

        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_asc","price_desc");
    }

    public enum  ProductStatusEnum {
       ON_SALE(1,"在线");



        private String value;
        private int code;

        ProductStatusEnum(int code ,String value){
            this.code=code;
            this.value = value;

        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
