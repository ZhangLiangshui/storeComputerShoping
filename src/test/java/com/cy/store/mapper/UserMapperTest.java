package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        User byUsername = userMapper.findByUsername("tim");
        System.out.println(byUsername);
    }
  @Test
    public void updatePasswordByUid() {
     userMapper.updatePasswordByUid(6,"520","管理员",new Date());
    }
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(6));
    }

    @Test
    public  void updateInfoByUid(){
        User user =new User();
        user.setUid(6);
        user.setPhone("1231234545");
        user.setEmail("33@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(7,"/upload/avatar.png","管理员",new Date());
    }

}