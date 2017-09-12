package com.aoshop.controller.portal;

import com.github.pagehelper.PageInfo;
import com.aoshop.common.Const;
import com.aoshop.common.ResponseCode;
import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.Shipping;
import com.aoshop.pojo.User;
import com.aoshop.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * author:liuao
 * description:
 * Date: create on 14:26 2017/9/8
 * modify by:
 */

@Controller
@RequestMapping("/shipping/")
public class ShippingController {
    @Autowired
    private IShippingService iShippingService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, Shipping shipping){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请重新登录");

        }
        return iShippingService.addShipping(user.getId(),shipping);
    }

    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse delete(HttpSession session, Integer shippingId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请重新登录");

        }
        return  iShippingService.delete(user.getId(),shippingId);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, Shipping shipping){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请重新登录");

        }
        shipping.setUserId(user.getId());
        return  iShippingService.update(shipping);
    }

    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse select(HttpSession session, Integer shippingId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请重新登录");

        }

        return  iShippingService.select(user.getId(),shippingId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum" ,defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请重新登录");

        }
        return iShippingService.list(user.getId(),pageNum,pageSize);

    }





}
