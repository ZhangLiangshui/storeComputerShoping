package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
@Test
public void insertOrder(){
    Order order = new Order();
    order.setUid(22);
    order.setRecvName("明明");
    order.setRecvPhone("1231223212");
    orderMapper.insertOrder(order);
}
@Test
public void insertOrderItem(){
    OrderItem order1=new OrderItem();
    order1.setOid(1);
    order1.setPid(10000003);
    order1.setTitle("张书记绝对符合的骄傲");
    orderMapper.insertOrderItem(order1);
}


}