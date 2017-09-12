package com.aoshop.service;


import com.aoshop.common.ServerResponse;
import com.aoshop.vo.CartVo;

/**
 * author:liuao
 * description:
 * Date: create on 21:03 2017/9/6
 * modify by:
 */
public interface ICartService {
    ServerResponse add(Integer userId, Integer count, Integer productId);

    ServerResponse<CartVo> update(Integer id, Integer productId, Integer count);

    ServerResponse<CartVo> delete(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
