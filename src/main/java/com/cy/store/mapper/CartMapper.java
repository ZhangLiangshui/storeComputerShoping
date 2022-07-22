package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;
public interface CartMapper {
    /**
     * 插入购物车的数据
     * @param cart
     * @return
     */
   Integer insert(Cart cart);

   Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
   Cart findByUidAndPid(Integer uid,Integer pid);
   List<CartVO> findVOByUid(Integer uid);
   Cart findByCid(Integer cid);
   List<CartVO> findVOByCid(Integer[] cids);

}
