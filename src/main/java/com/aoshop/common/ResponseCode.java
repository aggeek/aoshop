package com.aoshop.common;

/**
 * author:liuao
 * description:做一个类似于返回码的数据字典枚举
 * Date: create on 22:06 2017/8/16
 * modify by:
 */
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;

    private final String desc;

    ResponseCode(int code , String desc) {
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {
        return code;

    }

    public String getDesc() {
        return desc;
    }
}
