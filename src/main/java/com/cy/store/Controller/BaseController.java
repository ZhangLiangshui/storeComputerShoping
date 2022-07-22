package com.cy.store.Controller;

import com.cy.store.Controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/*
* 控制层类的基类*/
public class BaseController {
    public static final int OK=200;
    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    @ExceptionHandler({ServiceException.class,FileUploadException.class})//用来统一处理抛出的异常

  public JsonResult<Void> handleException(Throwable e){
   JsonResult<Void> result =new JsonResult<>(e);
   if(e instanceof UsernameDuplicatedException ){
          result.setState(4000);
          result.setMessage("用户名被占用");
      }else if(e instanceof InsertException){
       result.setState(5000);
       result.setMessage("插入数据产生未知的异常");
      }else if(e instanceof UserNotFoundException){
       result.setState(4001);
       result.setMessage("用户数据不存在的异常");
      }else if(e instanceof PasswordNotMatchException){
       result.setState(4002);
       result.setMessage("用户名密码错误");
      }else if(e instanceof AddressCountLimitException){
       result.setState(4003);
       result.setMessage("用户收购地址超出上限");
    }else if(e instanceof AddressNotFoundException){
       result.setState(4004);
       result.setMessage("用户收购地址数据不存在");
    }else if(e instanceof AccessDeniedException){
       result.setState(4005);
       result.setMessage("收货地址数据非法访问");
    }else if(e instanceof ProductNotFoundException){
       result.setState(4006);
       result.setMessage("商品数据不存在异常");
    }else if(e instanceof CartNotFoundException){
       result.setState(4007);
       result.setMessage("购物车数据不存在");
    } else if(e instanceof UpdateException){
       result.setState(5001);
       result.setMessage("更新数据时产生的异常");
    }else if(e instanceof DeleteException){
       result.setState(5002);
       result.setMessage("删除数据时产生的异常");
    }else if (e instanceof FileEmptyException) {
       result.setState(6000);
    } else if (e instanceof FileSizeException) {
       result.setState(6001);
    } else if (e instanceof FileTypeException) {
       result.setState(6002);
   } else if (e instanceof FileStateException) {
       result.setState(6003);
   } else if (e instanceof FileUploadIOException) {
       result.setState(6004);
   }
   return result;
    }
    //获取session对象中的uid
    //当前登录的用户uid的值
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());

    }
    //获取当前登录用户的用户名
    protected final String getUsernameFromSession(HttpSession session){
       return session.getAttribute("username").toString();
    }
}
