package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.util.Password;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private IUserService iUserService;
    @Test
    public void reg(){
        try {
            User user =new User();
            user.setUsername("taotao");
            user.setPassword("521");
            iUserService.reg(user);
            System.out.println("okok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User login = iUserService.login("zhangtao","521");
        System.out.println(login);
    }
    @Test
    public void changePassword(){
        iUserService.changePassword(7,"zhangtao","521","523");
    }

    @Test
    public void getByUid(){
        System.out.println(iUserService.getByUid(6));
    }
    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("123543455");
        user.setEmail("ting@qq.com");
        iUserService.changeInfo(6,"管理员",user);
    }
    @Test
    public void changeAvatar(){
        iUserService.changeAvatar(7,"/upload/test.png","小刚");
    }
}
