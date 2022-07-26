package com.cy.store.mapper;

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
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;
    @Test
    public void finByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for (District d:list){
            System.out.println(d);
        }
    }
    @Test
    public void findNameByCode(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }


}