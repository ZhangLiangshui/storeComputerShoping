package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/*表示收货地址持久层的接口*/
public interface AddressMapper {
    /*
    * 插入用户的地址数据*/
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址数量
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的地址数据
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收获地址数据
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid值修改用户的收货地址
     * @param uid
     * @return
     */
    Integer updateNonDefault(Integer uid);
    Integer updateDefaultByAid(
                              @Param("aid") Integer aid,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址id删除数据
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);
    Address findLastModified(Integer uid);

}
