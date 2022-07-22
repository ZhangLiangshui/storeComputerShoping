package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private IAddressService iAddressService;
    @Test
     public void addNewAddress(){
        Address address =new Address();
        address.setPhone("1232423412");
        address.setName("男朋友");
        iAddressService.addNewAddress(23,"管理员",address);
    }
    @Test
    public void setDefault(){
        iAddressService.setDefault(5,7,"管理员");
    }

@Test
    public void delete(){
        iAddressService.delete(3,7,"tom");
}
}
