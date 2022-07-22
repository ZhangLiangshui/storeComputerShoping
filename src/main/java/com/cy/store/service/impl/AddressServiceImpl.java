package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 新增收获地址的实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    //在添加用户的地址收获的业务层依赖于IDistrictService的业务接口
    @Autowired
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private int maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
           //调用收获地址统计的方法
        Integer count =addressMapper.countByUid(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("收获地址超出上限");
        }
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        Integer isDefault = count== 0 ? 1: 0;
        address.setIsDefault(isDefault);
        //补全4项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        //插入收获地址的方法
        Integer rows=addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户地址异常");

        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address:list) {
        /* address.setAid(null);
         address.setUid(null);*/
         address.setCityCode(null);
         address.setAreaCode(null);
         address.setZip(null);
         address.setTel(null);
         address.setCreatedTime(null);
         address.setIsDefault(null);
         address.setCreatedUser(null);
         address.setModifiedTime(null);
         address.setModifiedUser(null);
        }
        return list ;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址没找到");
        }
        //检测归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        //将所有地址设置为非默认
        Integer rows =addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        //将用户选中某条地址设置为默认收获地址
       rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收获地址数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows!=1) {
            throw new DeleteException("删除不成功异常");

        }
        Integer count = addressMapper.countByUid(uid);
        if(count==0){
            return;
        }
        if(result.getIsDefault()==0){
            return;
        }
        Address address = addressMapper.findLastModified(uid);

       rows= addressMapper.updateDefaultByAid(address.getAid(),username,new Date());
       if(rows!=1){
           throw new UpdateException("更新数据时产生未知的异常");
       }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if(address ==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        return address;
    }


}
