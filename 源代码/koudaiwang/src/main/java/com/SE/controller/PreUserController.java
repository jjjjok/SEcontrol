package com.SE.controller;


import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.dao.ItemDao;
import com.SE.dao.PageDao;
import com.SE.dao.UserDao;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class PreUserController {
    //转至注册页面
    @RequestMapping(value = "/toregister")
    public String toRegister(){
        return "register";
    }
    //转至登录界面
    @RequestMapping(value = "/tologin")
    public String toLogin(){
        return "login";
    }
    //注册
    @RequestMapping(value = "/register")
    public String userRegister(@RequestParam("username")String username,@RequestParam("userphone")String userphone,
                           @RequestParam("userpwd")String userpwd) {
        userinf user=new userinf();
        user.setUser_name(username);
        user.setUser_phone(userphone);
        user.setUser_pwd(userpwd);
        int i=UserDao.userInsert(user);
        if(i>0)
        return "login";
        else return "not";
    }
    //登录
    @RequestMapping(value = "/login")
    public void userLogin(@RequestParam("name")String username, @RequestParam("password")String userpwd, HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException {
        int a = UserDao.selectByName(username,userpwd);
        if (a>0){
            HttpSession session=req.getSession();
            userinf user =UserDao.selectAdmin(username,userpwd);
            session.setAttribute("name",user);
            session.setAttribute("isLogin","1");

            int cpage=1;//当前页
            int count=8;//每页显示数目
            String cp=req.getParameter("cp");
            if(cp!=null){
                cpage=Integer.parseInt(cp);
            }
            int result[]= PageDao.itemPage(count);

            ArrayList<item> list= ItemDao.selectPreAllItem(cpage,count);
            req.setAttribute("itemlist",list);
            req.setAttribute("itemsum",result[0]);
            req.setAttribute("itempage",result[1]);
            req.setAttribute("itemcpage",cpage);


            req.getRequestDispatcher("/index.jsp").forward(req,res);


        }else{
            PrintWriter out = res.getWriter();
            out.write("<script>");
            out.write("alert('ERROR');");
            out.write("location.href='${pageContext.request.contextPath}/user/tologin'");
            out.write("</script>");
            out.close();

        }

    }
    //检查用户名是否存在
    @RequestMapping(value = "/usernamecheck")
    @ResponseBody
    public void userNameCheck(@RequestParam("username")String username,HttpServletResponse res) throws IOException {
        int count=UserDao.userSearchByName(username);
        PrintWriter out = res.getWriter();
        if(count>0){
            out.print("false");
        }else{
            out.print("true");
        }
        out.close();
    }







}
