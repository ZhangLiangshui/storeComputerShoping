package com.cy.store.service;

import com.cy.store.entity.User;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user
     */
    void reg(User user);
/*
* 用户登录功能，匹配当前的用户数据，如果没有则返回null*/
     User login(String username,String password);
     void changePassword(Integer uid,
                         String username,
                         String oldPassword,
                         String newPassword);

        User getByUid(Integer uid);
        void changeInfo(Integer uid,String username,User user);

    /**
     * 修改用户的头像
     * @param uid
     * @param avatar
     * @param username
     */
        void changeAvatar(Integer uid,String avatar,String username);
}
