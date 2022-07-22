package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;
    @Test
    public void create(){
        Integer[]cids={3,4};
        Order order = orderService.create(7, cids, 7, "红红");
        System.out.println(order);
    }

}
