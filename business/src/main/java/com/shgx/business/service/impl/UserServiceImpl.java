package com.shgx.business.service.impl;

import com.shgx.business.respository.UserRepo;
import com.shgx.business.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shgx.business.model.User;
import javax.transaction.Transactional;

import java.util.Optional;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    /**
     * 查询用户是否存在
     * @param userId
     * @param password
     * @return
     */
    @Override
    public Boolean findUserByIdAndPass(Long userId, String password){
        Boolean login = false;
        Optional<User> users = userRepo.findUsersByUserIdAndAndPassword(userId, password);
        if(users.isPresent()){
            login = true;
        }
        return login;
    }

    /**
     * 用户注册
     * @param userId
     * @param userName
     * @param password
     * @return
     */
    @Override
    //@Transactional(rollbackOn = Exception.class)
    public Boolean save(Long userId, String userName, String password){
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        try{
            userRepo.save(user);
        }catch (Exception e){
            log.error("插入失败");
            return false;
        }
        return true;
    }
}
