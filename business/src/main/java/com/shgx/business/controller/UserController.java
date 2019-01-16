package com.shgx.business.controller;

import com.shgx.business.model.User;
import com.shgx.business.model.UserVO;
import com.shgx.business.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录请求
     * @param userVO
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserVO userVO){
        if(userVO==null){
            log.error("The user is null!");
        }
        Long userId = userVO.getUserId();
        //密码暂时不做加密处理
        String password = userVO.getPassword();
        Boolean access = userService.findUserByIdAndPass(userId, password);
        if(access){
            log.info("login success!");
            return "userId: "+userId+" login success!!!";
        }else{
            log.error("login failed!");
        }
        return "userId: "+userId+" login failed!!!";
    }

    /**
     * 用户注册请求
     * @param user
     * @return
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user){
        Long userId = user.getUserId();
        String userName = user.getUserName();
        String password = user.getPassword();
        if(userId==null||password==null){
            return "用户名和密码不能为空！";
        }
        if(userName==null){
            userName="new user";
        }
        if(userService.save(userId,userName,password)){
            log.info("{} success register!", userId.toString());
            return "注册成功";
        }
        log.error("{} register failed!", userId.toString());
        return "注册失败";
    }
}
