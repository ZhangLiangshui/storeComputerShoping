package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;

/**
 * 订单的持久层接口
 */
public interface OrderMapper {
    /**
     * 插入订单的数据
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项的数据
     * @param order
     * @return
     */
    Integer insertOrderItem(OrderItem order);
}
