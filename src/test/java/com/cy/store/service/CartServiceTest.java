package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class CartServiceTest {
    @Autowired
    private ICartService cartService;
    @Test
    public void addToCart(){
        cartService.addToCart(7,10000002,5,"张明");
    }


}
