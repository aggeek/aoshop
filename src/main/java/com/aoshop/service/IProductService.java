package com.aoshop.service;

import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.Product;
import com.aoshop.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;

/**
 * author:liuao
 * description:
 * Date: create on 21:41 2017/8/26
 * modify by:
 */
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
