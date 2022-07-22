package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
               //查询当前要添加的购物车是否存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date =new Date();
        if(result ==null){//表示这个商品从来没有添加到购物车中
            //创建一个cart对象
            Cart cart =new Cart();

            //补全数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
             //价格来自于商品中的价值
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
             //补全日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setCreatedTime(date);
            Integer rows = cartMapper.insert(cart);
            if(rows!=1){
                throw new InsertException("插入数据时产生未知的异常");
            }
        }else{//已经存在更新这个数据
         Integer num=  result.getNum()+amount;
        Integer rows= cartMapper.updateNumByCid(result.getCid(),num,username,date);
        if(rows!=1){
            throw new UpdateException("跟新数据时产生未知的异常");
        }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num=result.getNum()+1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新数据异常");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()){
            CartVO cartVO = it.next();
            if(!cartVO.getUid().equals(uid)){
                list.remove(cartVO);

            }
        }
        return list;
    }
}
