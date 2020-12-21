package com.SE.controller;

import com.SE.bean.item;
import com.SE.dao.FlDao;
import com.SE.dao.ItemDao;


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


    @RequestMapping(value = "/additem")
    public String addItem(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
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
        String name=r1.getParameter("name");
        String aprice1=r1.getParameter("aprice");
        String bprice1=r1.getParameter("bprice");
        String inf=r1.getParameter("content");
        String num1=r1.getParameter("num");
        String fl=r1.getParameter("fl");




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
        int c1= FlDao.linkInsert(fl_id,item_id);
        return "additem";


}}
