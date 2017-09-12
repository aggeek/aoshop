package com.aoshop.service;

import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * author:liuao
 * description:
 * Date: create on 14:27 2017/9/8
 * modify by:
 */
public interface IShippingService {

    ServerResponse addShipping(Integer userId, Shipping shipping);

    ServerResponse delete(Integer userId, Integer shippingId);

    ServerResponse update(Shipping shipping);

    ServerResponse select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
