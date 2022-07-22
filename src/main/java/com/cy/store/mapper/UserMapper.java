package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块的持久层接口
 * 插入用户的数据
 */
public interface UserMapper {
    Integer insert(User user);
    User findByUsername(String username);
    /*根据用户的uid来修改用户的密码*/
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);
    //根据用户的id查询用户的数据
    User findByUid(Integer uid);
    //更新用户的数据信息
    Integer updateInfoByUid(User user);
    //根据用户uid值来修改用户的头像
    Integer updateAvatarByUid(@Param("uid")Integer uid,
                              @Param("avatar")String avatar,
                              @Param("modifiedUser")String modifiedUser,
                              @Param("modifiedTime")Date modifiedTime);


}
