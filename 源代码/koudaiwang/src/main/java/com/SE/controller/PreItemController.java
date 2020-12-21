package com.SE.controller;


import com.SE.bean.*;
import com.SE.dao.*;
import com.SE.port.MimiPaySample;
import com.SE.port.PayParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/item")
public class PreItemController {
    //转至商品详情页
    @RequestMapping(value = "/todetail")
    public String toDetail(){
        return "prodetail";
    }
    @RequestMapping(value = "/detail")
    public void Detail(HttpServletResponse resp, HttpServletRequest req)throws ServletException, IOException{
        String id1=req.getParameter("id");
        int id=Integer.parseInt(id1);
        item i=ItemDao.selectItemById(id);
        req.setAttribute("i",i);


        req.getRequestDispatcher("/WEB-INF/jsp/prodetail.jsp").forward(req,resp);
    }


    //转至回调页
    @RequestMapping(value = "/topayok")
    public String toPayok(){
        return "payok";
    }



    //支付
    @RequestMapping(value = "/pay")
    public void dopay(HttpServletResponse response,HttpServletRequest req)throws ServletException, IOException{
        String confirm_id=req.getParameter("confirmid");
        String sum=req.getParameter("sum");
        HttpSession session=req.getSession();
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        //float sum= (float) 0.2;


        PayParams pp=new PayParams();
        pp.setPrice(Float.parseFloat(sum));
        pp.setType(2);
        pp.setOutTradeNo(confirm_id);
        pp.setOutUserNo(String.valueOf(uid));

        pp.setNotifyUrl("http://www.baidu.com");
        pp.setReturnUrl("http://www.baidu.com");
        MimiPaySample mm=new MimiPaySample();
        mm.pay(pp, response);
    }


    //首页点击分类显示商品
    @RequestMapping(value = "/selectflitem")
    public void selectFlItem(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{

        String id1=req.getParameter("id");
        int id=Integer.parseInt(id1);
        ArrayList<item> list= ItemDao.selectItemByFlId(id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/jsp/productlist.jsp").forward(req,resp);
    }
    //首页显示商品
    @RequestMapping(value = "/preselectallitem")
    public void selectPreAllItem(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
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
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
    //加入购物车
    @RequestMapping(value = "/collectadd")
    public void collectAdd(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        item i=new item();
        String item_id=req.getParameter("id");
        String count=req.getParameter("count");
        String url=req.getParameter("url");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")){
            int uid=user.getUser_id();
            if(item_id!=null){
                i=ItemDao.selectItemById(Integer.parseInt(item_id));
            }

            int flag=CollectDao.selectByUAI(uid,i.getItem_id());
           if(flag>0){
               int ccount=CollectDao.selectCollectNumByid(uid,i.getItem_id());
               int num=Integer.parseInt(count)+ccount;
               CollectDao.updateNum(num,uid,i.getItem_id());
               CollectDao.updateCINum(num,uid,i.getItem_id());
           }else {
               collect c=new collect();
               c.setUser_id(uid);
               c.setItem_id(i.getItem_id());
               c.setCollect_count(Integer.parseInt(count));
               c.setCollect_valid(1);
               CollectDao.collectInsert(c);
               collectitem l=new collectitem();
               l.setItem_id(i.getItem_id());
               l.setItem_inf(i.getItem_inf());
               l.setItem_name(i.getItem_name());
               l.setItem_aprice(i.getItem_aprice());
               l.setItem_bprice(i.getItem_bprice());
               l.setItem_img(i.getItem_img());
               l.setCollect_count(Integer.parseInt(count));
               l.setUser_id(uid);
               CollectDao.collectItemInsert(l);

           }

        }else{
            resp.sendRedirect("/WEB-INF/jsp/login.jsp");
        }

        if (url.equals("1")){
            resp.sendRedirect("collectshow");
        }else if(url.equals("2")){
            resp.sendRedirect("detail?id="+item_id);
        }
    }
    //购物车页面
    @RequestMapping(value = "/collectshow")
    public void collectShow(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            ArrayList<collectitem> list = CollectDao.selectCollectByUId(uid);



            req.setAttribute("list",list);

            req.getRequestDispatcher("/WEB-INF/jsp/collect.jsp").forward(req,resp);

        }
        else{
            resp.sendRedirect("/WEB-INF/jsp/login.jsp");
        }
    }
    //购物车数量加减
    @RequestMapping(value = "/collectnumadd")
    public void collectNumAdd(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        String count=req.getParameter("count");
        String itemid=req.getParameter("itemid");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        item i=new item();
        if(itemid!=null){
            i=ItemDao.selectItemById(Integer.parseInt(itemid));
        }

        CollectDao.updateNum(Integer.parseInt(count),uid,i.getItem_id());

        CollectDao.updateCINum(Integer.parseInt(count),uid,i.getItem_id());

    }
    //购物车删除
    @RequestMapping(value = "/collectdelete")
    public void collectDelete(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        String itemid=req.getParameter("itemid");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        item i=new item();
        if(itemid!=null){
            i=ItemDao.selectItemById(Integer.parseInt(itemid));
        }
        int a=CollectDao.collectDelete(uid,i.getItem_id());

    }
    //订单确认
    @RequestMapping(value = "/orderconfirm")
    public void orderConfirm(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        int confirmid= OrderDao.selectMaxCId();
        if(confirmid>0){
            confirmid+=1;
        }else {
            confirmid=1;
        }
        String itemids=req.getParameter("itemids");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        String ids[]=itemids.split(",");
        ArrayList<collectitem> list=new ArrayList<collectitem>();
        float sum=0;
        for(int i=0;i< ids.length;i++){
            collectitem p=CollectDao.selectCollectByUIId(uid,Integer.parseInt(ids[i]));
            int v=OrderDao.orderAdd(confirmid,uid,p.getItem_id(),p.getCollect_count());
            float price=p.getCollect_count()*p.getItem_bprice();
            sum+=price;
            list.add(p);
        }
        req.setAttribute("confirmlist",list);
        req.setAttribute("sum",sum);
        req.setAttribute("confirm",confirmid);
        req.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(req,resp);






    }




}
