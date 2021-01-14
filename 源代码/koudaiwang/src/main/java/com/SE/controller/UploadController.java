package com.SE.controller;

import com.SE.bean.*;
import com.SE.dao.*;


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
        response.setCharacterEncoding("GBK");
        request.setCharacterEncoding("GBK");
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
       // String name =new String(name1.getBytes(),"utf-8");
        //String inf =new String(inf1.getBytes(),"utf-8");


        item i=new item();
        if(!name1.equals("")&&!aprice1.equals("")&&!bprice1.equals("")&&!inf1.equals("")&&!num1.equals("")&&!fname.equals("")&&!fl.equals("")){



        Float aprice=Float.parseFloat(aprice1);
        Float bprice=Float.parseFloat(bprice1);
        int num=Integer.parseInt(num1);
        i.setItem_name(name1);
        i.setItem_aprice(aprice);
        i.setItem_bprice(bprice);
        i.setItem_inf(inf1);
        i.setItem_num(num);
        i.setItem_img(fname);
        int fl_id=Integer.parseInt(fl);
        int count= ItemDao.itemInsert(i);
        int item_id=ItemDao.selectIdByName(name1);
        int c1= FlDao.linkInsert(fl_id,item_id);
        return "additem";}
        else{
            return "additem";
        }


}//发布物品
    @RequestMapping(value = "/useradditem")
    public void addItemU(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
        response.setCharacterEncoding("GBK");
        request.setCharacterEncoding("GBK");
        HttpSession session=request.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")){
            int user_id=user.getUser_id();
        SmartUpload su=new SmartUpload();


        su.initialize(servletConfig,request,response);
        su.setAllowedFilesList("png,jpg");
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
        String name=r1.getParameter("name");
        String aprice1=r1.getParameter("aprice");
        String bprice1=r1.getParameter("bprice");
        String inf=r1.getParameter("content");
        String num1=r1.getParameter("num");
        String fl=r1.getParameter("fl");

        if(!name.equals("")&&!aprice1.equals("")&&!bprice1.equals("")&&!inf.equals("")&&!num1.equals("")&&!fname.equals("")&&!fl.equals("")){


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

    //退款
    @RequestMapping(value = "/refund")
    public void refund(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
        response.setCharacterEncoding("GBK");
        request.setCharacterEncoding("GBK");
            HttpSession session=request.getSession();
            userinf user=(userinf)session.getAttribute("name");
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
            String inf1=r1.getParameter("content");
            String fl=r1.getParameter("fl");
            String order_id=request.getParameter("orderid");
            int oid=Integer.parseInt(order_id);

            if(!inf1.equals("")&&!fl.equals("")){

                String reason="";

                refund i=new refund();
                i.setRefund_inf(inf1);
                i.setRefund_img(fname);

                int fl_id=Integer.parseInt(fl);
                if(fl_id==1){
                    reason="我不想要了";

                }else if(fl_id==2){
                    reason="没收到货";
                }else if(fl_id==3){
                    reason="商品与实物不符";
                }else if(fl_id==4){
                    reason="未按约定时间发货";
                }else if(fl_id==5){
                    reason="拍错了";
                }
                i.setRefund_rea(reason);
                i.setUser_id(user_id);
                i.setOrder_id(oid);


                RefundDao.refundInsert(i);
                OrderDao.updateOrderRefund(Integer.parseInt(order_id));
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('退款信息填写成功，请等待处理结果');");
                out.write("location.href='/kdw/user/myorder'");
                out.write("</script>");
                out.close();



            }
            else{
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('填写信息失败，输入不能为空！');");
                out.write("location.href='/kdw/user/myorder'");
                out.write("</script>");
                out.close();
            }


        }


    //换货
    @RequestMapping(value = "/goodchange")
    public void goodChange(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
        response.setCharacterEncoding("GBK");
        request.setCharacterEncoding("GBK");
        HttpSession session=request.getSession();
        userinf user=(userinf)session.getAttribute("name");
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
        String inf1=r1.getParameter("content");
        String fl=r1.getParameter("fl");
        String order_id=request.getParameter("orderid");
        int oid=Integer.parseInt(order_id);

        if(!inf1.equals("")&&!fl.equals("")){

            String reason="";

            goodchange i=new goodchange();
            i.setChange_inf(inf1);
            i.setChange_img(fname);

            int fl_id=Integer.parseInt(fl);
            if(fl_id==1){
                reason="收到的物品损坏了";

            }else if(fl_id==2){
                reason="商家发错了";
            }else if(fl_id==3){
                reason="我想换一件";
            }
            i.setChange_rea(reason);
            i.setUser_id(user_id);
            i.setOrder_id(oid);


            GoodChangeDao.goodChangeInsert(i);
            OrderDao.updateOrderChange(Integer.parseInt(order_id));
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('换货信息填写成功，请等待处理结果');");
            out.write("location.href='/kdw/user/myorder'");
            out.write("</script>");
            out.close();



        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('填写信息失败，输入不能为空！');");
            out.write("location.href='/kdw/user/myorder'");
            out.write("</script>");
            out.close();
        }


    }
    //申诉
    @RequestMapping(value = "/appeal")
    public void appeal(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
        response.setCharacterEncoding("GBK");
        request.setCharacterEncoding("GBK");
        HttpSession session=request.getSession();
        userinf user=(userinf)session.getAttribute("name");
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
        String inf1=r1.getParameter("content");
        String fl=r1.getParameter("fl");
        String order_id=request.getParameter("orderid");
        int oid=Integer.parseInt(order_id);

        if(!inf1.equals("")&&!fl.equals("")){

            String reason="";
            appeal i=new appeal();
            i.setAppeal_inf(inf1);
            i.setAppeal_img(fname);

            int fl_id=Integer.parseInt(fl);
            if(fl_id==1){
                reason="我的退款请求被拒绝了";

            }else if(fl_id==2){
                reason="商家有不诚信行为";
            }else if(fl_id==3){
                reason="其他";
            }
            i.setAppeal_rea(reason);
            i.setUser_id(user_id);
            i.setOrder_id(oid);


            AppealDao.appealInsert(i);
            OrderDao.updateOrderAppeal(Integer.parseInt(order_id));
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('申诉信息填写成功，请等待处理结果');");
            out.write("location.href='/kdw/user/myorder'");
            out.write("</script>");
            out.close();



        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('填写信息失败，输入不能为空！');");
            out.write("location.href='/kdw/user/myorder'");
            out.write("</script>");
            out.close();
        }


    }


}
