package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;

   @Test
    public void insert(){
       Cart c =new Cart();
       c.setUid(6);
       c.setPid(10000011);
       c.setNum(2);
       c.setPrice(1000L);
       cartMapper.insert(c);
   }
   @Test
   public void updateNumByCid(){
       cartMapper.updateNumByCid(1,4,"张三",new Date());
   }
     @Test
    public void find(){
    Cart byUidAndPid = cartMapper.findByUidAndPid(6, 10000011);
    System.err.println(byUidAndPid);
      }
    @Test
    public void findVOByUid(){

        System.err.println(cartMapper.findVOByUid(7));
    }
    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }
    @Test
    public void findVOByCid(){
       Integer[]cids={1,3,4,5,6};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}