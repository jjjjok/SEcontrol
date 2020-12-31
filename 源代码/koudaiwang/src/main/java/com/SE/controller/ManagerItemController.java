package com.SE.controller;

import com.SE.bean.fl;
import com.SE.bean.item;
import com.SE.bean.orderinf;
import com.SE.bean.userinf;
import com.SE.dao.*;
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
import javax.servlet.http.HttpSession;
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
    //后台管理搜索商品
    @RequestMapping(value = "/selectitembyname")
    public void selectUserByName(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        int cpage=1;//当前页
        int count=3;//每页显示数目
        String cp=req.getParameter("cp");
        String name=req.getParameter("keywords");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        int result[]= PageDao.itemNamePage(count,name);

        ArrayList<item> list=ItemDao.selectItemByName(cpage,count,name);
        req.setAttribute("itemlist",list);
        req.setAttribute("itemsum",result[0]);
        req.setAttribute("itempage",result[1]);
        req.setAttribute("itemcpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/itemmanager.jsp").forward(req,resp);
    }

    //后台管理单个删除商品
    @RequestMapping(value = "/deleteitem")
    public void itemDeleteManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");
        int a=ItemDao.itemDelete(Integer.parseInt(id));
        resp.sendRedirect("selectallitem?cp="+req.getParameter("cpage"));

    }
    //后台管理删除多个商品
    @RequestMapping(value = "/deleteitems")
    public void itemsDeleteManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id[] = req.getParameterValues("id[]");


        for(int i=0;i< id.length;i++){
            ItemDao.itemDelete(Integer.parseInt(id[i]));
        }
        resp.sendRedirect("selectallitem");

    }
    //后台管理商品审核
    @RequestMapping(value = "/itemcheck")
    public void itemCheck(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        int cpage=1;//当前页
        int count=5;//每页显示数目
        String cp=req.getParameter("cp");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        int result[]= PageDao.itemCheckPage(count);

        ArrayList<item> list= ItemDao.selectAllCheckItem(cpage,count);
        req.setAttribute("itemlist",list);
        req.setAttribute("itemsum",result[0]);
        req.setAttribute("itempage",result[1]);
        req.setAttribute("itemcpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/itemcheck.jsp").forward(req,resp);
    }
    //通过审核
    @RequestMapping(value = "/itemchecked")
    public void itemChecked(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");
       ItemDao.updateCheck(Integer.parseInt(id));
        resp.sendRedirect("itemcheck?cp="+req.getParameter("cpage"));

    }

    //管理员转账确认
    @RequestMapping(value = "/paychecked")
    public void payChecked(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");

        ItemDao.payChecked(Integer.parseInt(id));
        resp.sendRedirect("ordermanager?cp="+req.getParameter("cpage"));

    }



    //订单管理
    @RequestMapping(value = "/ordermanager")
    public void orderManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{

        int cpage=1;//当前页
        int count=5;//每页显示数目
        String cp=req.getParameter("cp");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        int result[]= PageDao.orderManagerPage(count);
        ArrayList<orderinf> olist=ItemDao.selectAllOrder(cpage,count);


        req.setAttribute("orderlist",olist);
        req.setAttribute("ordersum",result[0]);
        req.setAttribute("orderpage",result[1]);
        req.setAttribute("ordercpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/ordermanager.jsp").forward(req,resp);




    }


}
