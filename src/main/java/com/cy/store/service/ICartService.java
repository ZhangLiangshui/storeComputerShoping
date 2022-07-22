package com.cy.store.service;
import com.cy.store.vo.CartVO;

import java.util.List;
public interface ICartService {
    /**
     * 将商品添加到购物车中
     * @param uid
     * @param pid
     * @param amount
     * @param username
     */
   void addToCart(Integer uid,Integer pid,Integer amount,String username);
   List<CartVO> getVOByUid(Integer uid);

    /**
     * 更新用户的购物车数量
     * @param cid
     * @param uid
     * @param username
     * @return
     */
  Integer addNum(Integer cid,Integer uid,String username);
  List<CartVO> getVOByCid(Integer uid,Integer[] cids);
}
