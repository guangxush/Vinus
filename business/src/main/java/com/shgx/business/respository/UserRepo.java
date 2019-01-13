package com.shgx.business.respository;

import com.shgx.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Description 数据库操作类
 * @auther guangxush
 * @create 2019-01-13
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    /**
     * 查询id和密码是否匹配
     * @param userId
     * @param password
     * @return
     */
    Optional<User> findUsersByUserIdAndAndPassword(Long userId, String password);


}
