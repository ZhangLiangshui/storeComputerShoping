package com.cy.store.Controller;

import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("orders")
@RestController

public class OrderController extends BaseController {
  @Autowired
    private IOrderService orderService;
  @RequestMapping("create")
  public JsonResult<Order> create(Integer aid, Integer[]cids, HttpSession session){
      Integer uid = getuidFromSession(session);
      String username=getUsernameFromSession(session);
      Order order = orderService.create(aid, cids, uid, username);
      return new JsonResult<>(OK,order);

  }
}
