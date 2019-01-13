package com.shgx.business.service;

import com.shgx.business.model.User;

/**
 * @Description 用户操作类
 * @auther guangxush
 * @create 2019-01-13
 */
public interface UserService {
    /**
     * 查询用户是否存在
     * @param userId
     * @param password
     * @return
     */
    Boolean findUserByIdAndPass(Long userId, String password);

    /**
     * 用户注册
     * @param userId
     * @param userName
     * @param password
     * @return
     */
    Boolean save(Long userId, String userName, String password);
}
