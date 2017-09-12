package com.aoshop.service;

import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.Category;

import java.util.List;

/**
 * author:liuao
 * description:
 * Date: create on 21:38 2017/8/23
 * modify by:
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
