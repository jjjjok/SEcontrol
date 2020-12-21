package com.SE.controller;

import com.SE.bean.userinf;
import com.SE.dao.PageDao;
import com.SE.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/usermanager")
public class ManagerUserController {
    //后台管理显示用户
    @RequestMapping(value = "/selectalluser")
    public void selectAllUser(HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        int cpage=1;//当前页
        int count=3;//每页显示数目
        String cp=req.getParameter("cp");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        int result[]= PageDao.userPage(count);

        ArrayList<userinf> list= UserDao.selectAllUser(cpage,count);
        req.setAttribute("userlist",list);
        req.setAttribute("usersum",result[0]);
        req.setAttribute("userpage",result[1]);
        req.setAttribute("usercpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/usermanager.jsp").forward(req,resp);
    }
    //后台管理搜索用户
    @RequestMapping(value = "/selectuserbyname")
    public void selectUserByName(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        int cpage=1;//当前页
        int count=3;//每页显示数目
        String cp=req.getParameter("cp");
        String name=req.getParameter("keywords");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        int result[]= PageDao.userNamePage(count,name);

        ArrayList<userinf> list=UserDao.selectUserByName(cpage,count,name);
        req.setAttribute("userlist",list);
        req.setAttribute("usersum",result[0]);
        req.setAttribute("userpage",result[1]);
        req.setAttribute("usercpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/usermanager.jsp").forward(req,resp);
    }
//转至后台管理页面
    @RequestMapping(value = "/tomanager")
    public String toManager(){
        return "usermanager";
    }
    //转至用户更新页面
    @RequestMapping(value = "/touserupdatemanager")
    public String toUserUpdateManager(){
        return "userupdatemanager";
    }
    //管理员修改用户
    @RequestMapping(value = "/userupdatemanager")
    public void userUpdateManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String id1 = req.getParameter("id");
        int id=Integer.parseInt(id1);
        userinf user=UserDao.selectById(id);

        req.setAttribute("user",user);


        req.setAttribute("cpage",req.getAttribute("cpage"));

    }
    //后台管理单个删除用户
    @RequestMapping(value = "/deleteuser")
    public void userDeleteManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");
        int a=UserDao.userDelete(Integer.parseInt(id));
        resp.sendRedirect("selectalluser?cp="+req.getParameter("cpage"));

    }
    //后台管理多个删除用户
    @RequestMapping(value = "/deleteusers")
    public void usersDeleteManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id[] = req.getParameterValues("id[]");


        for(int i=0;i< id.length;i++){
        UserDao.userDelete(Integer.parseInt(id[i]));
        }
        resp.sendRedirect("selectalluser");

    }

}
