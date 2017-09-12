package com.aoshop.controller.backend;

import com.aoshop.common.Const;
import com.aoshop.common.ResponseCode;
import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.User;
import com.aoshop.service.ICategoryService;
import com.aoshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * author:liuao
 * description:
 * Date: create on 21:25 2017/8/23
 * modify by:
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("c.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session , String categoryName , @RequestParam (value = "parentId" ,defaultValue = "0") int parentId){
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请重新登录");

        }
        //校验管理员
       ServerResponse response= iUserService.checkAdminRole(user);
        if(response.isSuccess()) {
           return iCategoryService.addCategory(categoryName,parentId);
        }else {
            return  ServerResponse.createByErrorMessage("无权限");
        }
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,String categoryName ,  int categoryId){
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请重新登录");

        }
        //校验管理员
        ServerResponse response= iUserService.checkAdminRole(user);
        if(response.isSuccess()) {
            return iCategoryService.updateCategoryName(categoryName,categoryId);
        }else {
            return  ServerResponse.createByErrorMessage("无权限");
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    /**
     *@author:liuao
     *@description:获取下一层子节点的信息
     *@date:22:14 2017/8/23
     *@param: * @param session
     * @param categoryId
     */
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") int categoryId){

        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请重新登录");

        }
        //校验管理员
        ServerResponse response= iUserService.checkAdminRole(user);
        if(response.isSuccess()) {
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else {
            return  ServerResponse.createByErrorMessage("无权限");
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    /**
     *@author:liuao
     *@description:获取所有后代节点
     *@date:22:14 2017/8/23
     *@param: * @param session
     * @param categoryId
     */
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") int categoryId){

        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请重新登录");

        }
        //校验管理员
        ServerResponse response= iUserService.checkAdminRole(user);
        if(response.isSuccess()) {
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else {
            return  ServerResponse.createByErrorMessage("无权限");
        }
    }





}
