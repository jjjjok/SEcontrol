package com.SE.controller;

import com.SE.bean.item;
import com.SE.bean.manager;
import com.SE.bean.userinf;
import com.SE.dao.FlDao;
import com.SE.dao.ItemDao;


import com.SE.dao.SellDao;
import com.SE.dao.UserDao;
import com.jspsmart.upload.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController implements ServletConfigAware,ServletContextAware {
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext arg0) {
        this.servletContext = arg0;
    }
    private ServletConfig servletConfig;
    @Override
    public void setServletConfig(ServletConfig arg0) {
        this.servletConfig = arg0;
    }

//后台管理添加物品
    @RequestMapping(value = "/manadditem")
    public String addItem(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        manager user=(manager)session.getAttribute("man");
        int user_id=user.getMan_id();
        SmartUpload su=new SmartUpload();
        su.initialize(servletConfig,request,response);
        try {
            su.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Files fs=su.getFiles();
        File f=fs.getFile(0);
        String fname=f.getFileName();
        try {
            su.save("imgs");
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        Request r1=su.getRequest();
        String name1=r1.getParameter("name");
        String aprice1=r1.getParameter("aprice");
        String bprice1=r1.getParameter("bprice");
        String inf1=r1.getParameter("content");
        String num1=r1.getParameter("num");
        String fl=r1.getParameter("fl");
        String name =new String(name1.getBytes(),"utf-8");
        String inf =new String(inf1.getBytes(),"utf-8");


        item i=new item();
        Float aprice=Float.parseFloat(aprice1);
        Float bprice=Float.parseFloat(bprice1);
        int num=Integer.parseInt(num1);
        if(name!=null&&aprice1!=null&&bprice1!=null&&inf!=null&&num1!=null){

        i.setItem_name(name);
        i.setItem_aprice(aprice);
        i.setItem_bprice(bprice);
        i.setItem_inf(inf);
        i.setItem_num(num);
        i.setItem_img(fname);
        }
        int fl_id=Integer.parseInt(fl);
        int count= ItemDao.itemInsert(i);

        int item_id=ItemDao.selectIdByName(name);
        SellDao.insertSell(item_id,user_id,name,aprice,bprice,num,fname,inf);
        int c1= FlDao.linkInsert(fl_id,item_id);
        return "additem";


}//发布物品
    @RequestMapping(value = "/useradditem")
    public void addItemU(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")){
            int user_id=user.getUser_id();
        SmartUpload su=new SmartUpload();
        su.initialize(servletConfig,request,response);
        try {
            su.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Files fs=su.getFiles();
        File f=fs.getFile(0);
        String fname=f.getFileName();
        try {
            su.save("imgs");
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        Request r1=su.getRequest();
        String name1=r1.getParameter("name");
        String aprice1=r1.getParameter("aprice");
        String bprice1=r1.getParameter("bprice");
        String inf1=r1.getParameter("content");
        String num1=r1.getParameter("num");
        String fl=r1.getParameter("fl");

        if(!name1.equals("")&&!aprice1.equals("")&&!bprice1.equals("")&&!inf1.equals("")&&!num1.equals("")){
        String name =new String(name1.getBytes(),"utf-8");
        String inf =new String(inf1.getBytes(),"utf-8");


        item i=new item();
        Float aprice=Float.parseFloat(aprice1);
        Float bprice=Float.parseFloat(bprice1);
        int num=Integer.parseInt(num1);


            i.setItem_name(name);
            i.setItem_aprice(aprice);
            i.setItem_bprice(bprice);
            i.setItem_inf(inf);
            i.setItem_num(num);
            i.setItem_img(fname);

        int fl_id=Integer.parseInt(fl);
        int count= ItemDao.itemInsert(i);

        int item_id=ItemDao.selectIdByName(name);
        SellDao.insertSell(item_id,user_id,name,aprice,bprice,num,fname,inf);
        FlDao.linkInsert(fl_id,item_id);
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('发布成功，请等待管理员审核通过');");
            out.write("location.href='/kdw/item/tousersell'");
            out.write("</script>");
            out.close();



        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('添加失败，输入不能为空！');");
            out.write("location.href='/kdw/item/tousersell'");
            out.write("</script>");
            out.close();
        }


    }}


}
