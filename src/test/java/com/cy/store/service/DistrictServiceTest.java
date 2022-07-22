package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest//标注当前的测试类
@RunWith(SpringRunner.class)
public class DistrictServiceTest {
    @Autowired
    private IDistrictService districtService;
    @Test
    public void getByParent(){
        //86表示中国
        List<District> list =districtService.getByParent("86");
        for (District d:list) {
            System.err.println(d);
        }
    }



}
