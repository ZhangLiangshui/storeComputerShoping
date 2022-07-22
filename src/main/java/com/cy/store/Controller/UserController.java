package com.cy.store.Controller;

import com.cy.store.Controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.service.impl.UserServiceImpl;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")

public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
    @RequestMapping("reg")
    public JsonResult<Void> reg (User user){
            userService.reg(user);
     return new JsonResult<>(OK);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
       User data =userService.login(username,password);
       //向session对象中完成数据的绑定
       session.setAttribute("uid",data.getUid());
       session.setAttribute("username",data.getUsername());
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        // 调用session.getAttribute("")获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象执行修改密码
        userService.changePassword(uid, username, oldPassword, newPassword);
        // 返回成功
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
           userService.changeInfo(uid,username,user);
           return new JsonResult<>(OK);
    }
    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE=10*1024*1024;
    //限制上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
     static {
         AVATAR_TYPE.add("image/jpeg");
         AVATAR_TYPE.add("image/png");
         AVATAR_TYPE.add("image/bmp");
         AVATAR_TYPE.add("image/gif");

}
@RequestMapping("change_avatar")
    public JsonResult<String>changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file){
         //判断文件是否为空
        if(file.isEmpty()){
           throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件类型是否是我们规定的后缀和类型
        String contentType = file.getContentType();
        //如果集合包含某个元素
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件
        String parent = session.getServletContext().getRealPath("upload");
        //File对象指向这个路径是否存在
        File dir = new File(parent);
        if(!dir.exists()){//检测目录是否存在
            dir.mkdirs();//创建当前的目录
        }
              //获取到这个文件的名称，UUid生成一个新的字符窜作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename"+originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix =originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest = new File(dir,filename);//是一个空文件
        //参数file中的数据写入到这个空文件中
        try {
            file.transferTo(dest);//将file文件写入到dest文件中
        } catch (FileStateException e) {
           throw new FileUploadIOException("文件状态异常");
        }catch (IOException e){
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid =getuidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/"+filename;
        userService.changeAvatar(uid,username,avatar);
        //返回用户头像的路径给前端页面，用于头像的展示
        return new JsonResult<>(OK,avatar);

    }

}
