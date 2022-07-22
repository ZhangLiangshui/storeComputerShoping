package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

@Test
    public void insert(){
    Address address =new Address();
    address.setUid(23);
    address.setPhone("178123213");
    address.setName("女朋友");
    addressMapper.insert(address);
 }
 @Test
    public void countByUid(){
    Integer count = addressMapper.countByUid(1);
     System.out.println(count);
 }
 @Test
    public void findByUid(){
     List<Address> list = addressMapper.findByUid(23);
     System.out.println(list);
 }
 @Test
    public void findByAid(){
     Address byAid = addressMapper.findByAid(5);
     System.err.println(byAid);
 }
    @Test
    public void updateNonDefault(){
        Integer integer = addressMapper.updateNonDefault(7);
        System.out.println(integer);
    }
    @Test
    public void updateDefaultByAid(){
 addressMapper.updateDefaultByAid(7,"涛涛",new Date());
    }
    @Test
    public void deleteByAid(){
         addressMapper.deleteByAid(2);
    }
    @Test
    public void  findLasModified(){
        System.out.println(addressMapper.findLastModified(7));
    }

}