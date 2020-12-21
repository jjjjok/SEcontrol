package com.SE.controller;

import com.SE.bean.fl;
import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.dao.FlDao;
import com.SE.dao.ItemDao;
import com.SE.dao.PageDao;
import com.SE.dao.UserDao;
import com.SE.port.MimiPaySample;

import com.SE.port.PayParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;

@Controller
@RequestMapping("/itemmanager")
public class ManagerItemController {

    //转至商品管理页面
    @RequestMapping(value = "/toitemmanager")
    public String toItemManager(){
        return "itemmanager";
    }


    //后台管理显示商品
    @RequestMapping(value = "/selectallitem")
    public void selectAllItem(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        int cpage=1;//当前页
        int count=2;//每页显示数目
        String cp=req.getParameter("cp");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        int result[]= PageDao.itemPage(count);

        ArrayList<item> list= ItemDao.selectAllItem(cpage,count);
        req.setAttribute("itemlist",list);
        req.setAttribute("itemsum",result[0]);
        req.setAttribute("itempage",result[1]);
        req.setAttribute("itemcpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/itemmanager.jsp").forward(req,resp);
    }
    //转至发布商品页面
    @RequestMapping(value = "/toadditem")
    public String toAddItem(){
        return "additem";
    }


}
