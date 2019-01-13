package com.shgx.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Entity
@Data
@Table(name = "user")
public class User {
    /**
     * 用户自增主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id，用于登录
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

}
