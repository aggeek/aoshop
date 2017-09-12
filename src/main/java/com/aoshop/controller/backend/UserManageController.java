package com.aoshop.controller.backend;

import com.aoshop.common.Const;
import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.User;
import com.aoshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * author:liuao
 * description:
 * Date: create on 21:08 2017/8/21
 * modify by:
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value= "login.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String  username, String password , HttpSession session) {
        ServerResponse<User>  response = iUserService.login(username ,password);

        if(response.isSuccess()) {
            User user = response.getData();
            if(user.getRole().equals(Const.role.ROLE_ADMIN)) {
                session.setAttribute(Const.CURRENT_USER,user);
                return  response;
            } else{
            return  ServerResponse.createByErrorMessage("非管理员用户");

            }
        }
        return  response;
    }
}
