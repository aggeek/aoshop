package com.aoshop.service.Impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.aoshop.common.ServerResponse;
import com.aoshop.dao.CategoryMapper;
import com.aoshop.pojo.Category;
import com.aoshop.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;


/**
 * author:liuao
 * description:
 * Date: create on 21:38 2017/8/23
 * modify by:
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    public CategoryMapper categoryMapper;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);


    public ServerResponse addCategory(String categoryName , Integer parentId) {
        if (parentId== null || StringUtils.isBlank(categoryName)) {
            return  ServerResponse.createByErrorMessage("添加类别参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//有效

        int rowCount = categoryMapper.insert(category);

        if(rowCount>0) {
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    public ServerResponse updateCategoryName(String categoryName , Integer categoryId) {
        if (categoryId== null || StringUtils.isBlank(categoryName)) {
            return  ServerResponse.createByErrorMessage("修改类别参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryId);
       // category.setStatus(true);//有效

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);

        if(rowCount>0) {
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory( Integer categoryId) {
        if (categoryId== null ) {
            return  ServerResponse.createByErrorMessage("修改类别参数错误");
        }

        List<Category> list = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if(CollectionUtils.isEmpty(list)){
            logger.info("未找到当前节点的子节点");

        }
        return  ServerResponse.createBySuccess(list);
    }

    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildrenCatergory(categorySet,categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null) {
            for (Category categorySetItem:categorySet) {
                categoryIdList.add(categorySetItem.getId());
                
            }
        }
        return  ServerResponse.createBySuccess(categoryIdList);
    }

    public Set<Category> findChildrenCatergory (Set<Category> categorySet ,Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null )
        {
            categorySet.add(category);
        }
        List<Category> list = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem:list) {
            findChildrenCatergory(categorySet,categoryItem.getId());
        }
        return  categorySet;


    }






}
