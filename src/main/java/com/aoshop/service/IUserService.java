package com.aoshop.service;


import com.aoshop.common.ServerResponse;
import com.aoshop.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * author:liuao
 * description:
 * Date: create on 21:52 2017/8/16
 * modify by:
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> forgetCheckAnswer(String username, String question, String answer);

    ServerResponse<String > forgetResetPassword(String username, String passwordNew, String token);

    ServerResponse<String>  resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateUserInformation(HttpSession session, User user);

    ServerResponse<User> getUserInformation(int userId);
    ServerResponse checkAdminRole(User user);


}
