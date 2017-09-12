package com.aoshop.controller.portal;

import com.aoshop.common.Const;
import com.aoshop.common.ResponseCode;
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
 * Date: create on 21:42 2017/8/16
 * modify by:
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    /**
     *@author:liuao
     *@description:登录
     *@date:21:48 2017/8/16
     *@param: * @param username
     * @param password
     */
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value= "login.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String  username, String password , HttpSession session) {

        //service
       ServerResponse<User> response= iUserService.login(username , password);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value= "logout.do" , method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> logout( HttpSession session) {

        //service
        session.removeAttribute(Const.CURRENT_USER);
        return  ServerResponse.createBySuccess();
    }

    @RequestMapping(value= "register.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register( User user) {

        //service
        ServerResponse<String > response = iUserService.register(user);

        return response;

    }
    @RequestMapping(value= "check_valid.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str ,String type) {

        return  iUserService.checkValid(str , type);
    }

    @RequestMapping(value= "get_user_info.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo (HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if ( user != null ) {
            return ServerResponse.createBySuccess(user);
        }
        return  ServerResponse.createBySuccessMessage("用户未登录，无法获取当前用户信息");
    }

    @RequestMapping(value= "forget_get_question.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String > forgetGetQuestion(String username) {

        return  iUserService.selectQuestion(username);
    }

    @RequestMapping(value= "forget_check_answer.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String > forgetCheckAnswer(String username,String question,String  answer){
        return iUserService.forgetCheckAnswer(username,question,answer);
    }

    @RequestMapping(value= "forget_reset_password.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String > forgetResetPassword(String username,String passwordNew,String token) {
        return  iUserService.forgetResetPassword( username, passwordNew, token);
    }
    @RequestMapping(value= "reset_password.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String>  resetPassword(String passwordOld ,String passwordNew ,HttpSession session) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorMessage("重新登录");
        }

        return  iUserService.resetPassword(passwordOld,passwordNew,user);


    }
    @RequestMapping(value= "update_information.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInformation(HttpSession session ,User user) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null) {
            return ServerResponse.createByErrorMessage("重新登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());

        ServerResponse<User> response = iUserService.updateUserInformation(session , user);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER , response.getData());
        }
        return  response;

    }

    @RequestMapping(value= "get_information.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInformation (HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"需要强制登录返回10code");

        }
        return iUserService.getUserInformation(currentUser.getId());
    }

}
