package com.aoshop.service.Impl;

import com.aoshop.common.Const;
import com.aoshop.common.ServerResponse;
import com.aoshop.common.TokenCache;
import com.aoshop.dao.UserMapper;
import com.aoshop.pojo.User;
import com.aoshop.service.IUserService;
import com.aoshop.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * author:liuao
 * description:
 * Date: create on 21:53 2017/8/16
 * modify by:
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
   public UserMapper userMapper;
   public  ServerResponse<User> login(String username, String password){

       int resultCount =userMapper.checkUsername(username);
       if(resultCount==0) {
           return  ServerResponse.createByErrorMessage("用户名不存在");
       }

       //md5密码验证
       String md5Password = MD5Util.MD5EncodeUtf8(password);

       User user=userMapper.selectLogin(username, md5Password) ;

       if(user == null) {
           return  ServerResponse.createByErrorMessage("密码错误");
       }
       user.setPassword(StringUtils.EMPTY);

       return  ServerResponse.createBySuccess("登录成功",user);
   }

    @Override
    public ServerResponse<String> register(User user) {
       ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
       if (!validResponse.isSuccess()) {
           return  validResponse;
       }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return  validResponse;
        }

        user.setRole(Const.role.ROLE_CUSTOMER);
        //密码加密md5
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
       int resultCount = userMapper.insert(user);

        if( resultCount == 0) {
            return  ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");

    }
    //判断用户名和是否存在
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if( StringUtils.isNotBlank(type)) {

            if(Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return  ServerResponse.createByErrorMessage("用户名已存在");
                }
            }

            if(Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return  ServerResponse.createByErrorMessage("邮箱地址已存在");
                }
            }

        }else {
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<String> selectQuestion (String username) {
       ServerResponse response = this.checkValid( username,Const.USERNAME)  ;
       if (response.isSuccess()) {
           return  ServerResponse.createByErrorMessage("用户名不存在");
       }
      String question= userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)) {
            return  ServerResponse.createBySuccess(question);
        }
        return  ServerResponse.createByErrorMessage("找回密码问题为空");
    }

    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer) {
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount>0) {
            //说明问题答案验证正确，给用户生成一个有时间限制的token，返回给客户端的授权验证
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return  ServerResponse.createBySuccess(forgetToken);
        }
            return  ServerResponse.createByErrorMessage("问题验证错误");
    }

    public ServerResponse<String > forgetResetPassword(String username,String passwordNew,String token) {
        ServerResponse response = this.checkValid( username,Const.USERNAME)  ;
        if (response.isSuccess()) {
            return  ServerResponse.createByErrorMessage("用户名不存在");
        }
        if(StringUtils.isBlank(token)) {
            return  ServerResponse.createByErrorMessage("token无效，重新输入密码提示问题及答案");
        }
        String trueToken = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.equals(token,trueToken)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username , passwordNew);

            if(rowCount>0) {
                return  ServerResponse.createBySuccessMessage("修改密码成功");
            } else {
                return  ServerResponse.createByErrorMessage("修改密码失败");
            }

        }

        return ServerResponse.createByErrorMessage("请求超时，请重新输入密码提示问题");
    }

    public ServerResponse<String>  resetPassword(String passwordOld ,String passwordNew ,User user) {

       int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
       if(resultCount==0) {
           return  ServerResponse.createByErrorMessage("密码输入错误");
       }
       String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);

       user.setPassword(md5Password);
       int updateCount = userMapper.updateByPrimaryKeySelective(user);
       if(updateCount>0) {
           return  ServerResponse.createBySuccessMessage("密码修改成功");
       }

       return ServerResponse.createByErrorMessage("密码修改失败");

    }

    public ServerResponse<User> updateUserInformation(HttpSession session ,User user) {
       //username不允许更新，email也要进行判断重复
       int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());

       if(resultCount>0) {
           return  ServerResponse.createByErrorMessage("邮箱重复");
       }
       User newUser = new User();
       newUser.setId(user.getId());
       newUser.setEmail(user.getEmail());
       newUser.setPhone(user.getPhone());
       newUser.setQuestion(user.getQuestion());
       newUser.setAnswer(user.getAnswer());

       int updateCount = userMapper.updateByPrimaryKeySelective(newUser);

       if(updateCount>0) {
           return  ServerResponse.createBySuccess("更新成功" , newUser);
       }
       return  ServerResponse.createByErrorMessage("更新失败");


    }

    public ServerResponse<User> getUserInformation (int userId) {
       User user = userMapper.selectByPrimaryKey(userId);
       if (user == null) {
           return ServerResponse.createByErrorMessage("找不到当前用户");

       }
       user.setPassword(StringUtils.EMPTY);
        return  ServerResponse.createBySuccess(user);
    }
    //校验是否为管理员
    public ServerResponse checkAdminRole (User user) {
       if(user!=null&&user.getRole().intValue()==Const.role.ROLE_ADMIN){
           return  ServerResponse.createBySuccess();
       }
       return  ServerResponse.createByError();

    }

}
