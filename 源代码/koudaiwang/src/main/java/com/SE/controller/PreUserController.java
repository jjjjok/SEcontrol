package com.SE.controller;


import com.SE.bean.*;
import com.SE.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
    //转至忘记密码界面
    @RequestMapping(value = "/toforget")
    public String toForget(){
        return "forget";
    }
    //转至添加地址页面
    @RequestMapping(value = "/toaddaddress")
    public String toAddAddress(){
        return "addaddress";
    }


    //转至修改地址页面
    @RequestMapping(value = "/tochangeaddress")
    public void toAddAddress(@RequestParam("id")String aid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{
        address a=UserDao.selectAddressByAddId(Integer.parseInt(aid));
        req.setAttribute("addlist", a);
        req.getRequestDispatcher("/WEB-INF/jsp/changeaddress.jsp").forward(req, res);

    }

    //转至添加物流页面
    @RequestMapping(value = "/toaddsend")
    public void toAddSend(@RequestParam("id")String oid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{

        req.setAttribute("oid", oid);
        req.getRequestDispatcher("/WEB-INF/jsp/addsend.jsp").forward(req, res);

    }

    //转至个人界面
    @RequestMapping(value = "/tomine")
    public String toMine(){
        return "mine";
    }
    //注册
    @RequestMapping(value = "/register")
    public void userRegister(@RequestParam("username")String username,@RequestParam("userphone")String userphone,@RequestParam("pay")String pay,
                           @RequestParam("userpwd")String userpwd,HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");

        String name = new String(username.getBytes("ISO-8859-1"),"UTF-8");
        userinf user=new userinf();
        user.setUser_name(name);
        user.setUser_phone(userphone);
        user.setUser_pay(pay);
        user.setUser_pwd(userpwd);
        int i=UserDao.userInsert(user);
        PrintWriter out = res.getWriter();
        out.write("<script>");
        out.write("alert('注册成功');");
        out.write("location.href='/kdw/user/tologin'");
        out.write("</script>");
        out.close();

    }
    //登录
    @RequestMapping(value = "/login")
    public void userLogin(@RequestParam("name")String username, @RequestParam("password")String userpwd, HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        int a = UserDao.selectByName(username,userpwd);
        if (a>0){
            HttpSession session=req.getSession();
            userinf user =UserDao.selectAdmin(username,userpwd);
            session.setAttribute("name",user);
            session.setAttribute("isLogin","1");

            res.sendRedirect("/kdw/item/preselectallitem");


        }else{
            PrintWriter out = res.getWriter();
            out.write("<script>");
            out.write("alert('用户名或密码错误');");
            out.write("location.href='/kdw/user/tologin'");
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

    //检查手机号是否匹配
    @RequestMapping(value = "/userphonecheck")
    @ResponseBody
    public void userPhoneCheck(@RequestParam("username")String username,@RequestParam("userphone")String userphone, HttpServletResponse res) throws IOException {
        if(username!=null){
            int count =UserDao.userSearchByNameAndPhone(username,userphone);

        PrintWriter out = res.getWriter();
        if(count>0){
            out.print("false");
        }else{
            out.print("true");
        }
        out.close();
    }}

    //修改密码
    @RequestMapping(value = "/forget")
    @ResponseBody
    public void updatePwd(@RequestParam("username")String username,@RequestParam("userpwd")String pwd ,HttpServletResponse res,HttpServletRequest req) throws IOException {
        UserDao.updatePwd(username,pwd);
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.write("<script>");
        out.write("alert('修改成功！');");
        out.write("location.href='/kdw/user/tologin'");
        out.write("</script>");
        out.close();

    }
//显示我的订单
    @RequestMapping(value = "/myorder")
    public void myOrder(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInf(uid);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myorder.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }


    }
    //用户确认收货
    @RequestMapping(value = "/itemconfirm")
    public void payChecked(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");

        OrderDao.itemConfirm(Integer.parseInt(id));
        resp.sendRedirect("myorder");

    }

    //显示等待发货
    @RequestMapping(value = "/myselltosend")
    public void mySellToSend(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> olist=OrderDao.selectOrderInfToSend(uid);


            req.setAttribute("orderlist", olist);

            req.getRequestDispatcher("/WEB-INF/jsp/mysell.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }


    }

    //显示等待用户收货
    @RequestMapping(value = "/mysellsended")
    public void mySellSended(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> olist=OrderDao.selectOrderInfSended(uid);


            req.setAttribute("orderlist", olist);

            req.getRequestDispatcher("/WEB-INF/jsp/mysellsended.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }

    }

    //添加物流信息
    @RequestMapping("/addsend")

    public void addSend(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String order_id1=req.getParameter("oid");
        String name=req.getParameter("name");
        String wuliu_id=req.getParameter("sendid");

            int order_id = Integer.parseInt(order_id1);
            SellDao.insertSend(order_id, name, wuliu_id);

            resp.sendRedirect("myselltosend");
        }

    //地址管理
    @RequestMapping(value = "/addressmanager")
    public void addressManager(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        ArrayList<address> add=UserDao.selectUserAddress(uid);
        req.setAttribute("addlist",add);
        req.getRequestDispatcher("/WEB-INF/jsp/address.jsp").forward(req,resp);
    }
    //添加收货地址
    @RequestMapping(value = "/addressadd")
    public void addAddress(@RequestParam("name")String username,@RequestParam("userphone")String userphone,@RequestParam("address")String address1,HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        if(!username.equals("")&&!userphone.equals("")&&!address1.equals("")) {

            String name = new String(username.getBytes("ISO-8859-1"),"UTF-8");
            String address = new String(address1.getBytes("ISO-8859-1"),"UTF-8");

            HttpSession session=req.getSession();
            userinf user=(userinf)session.getAttribute("name");
            int uid=user.getUser_id();

            address a = new address();
            a.setUser_name(name);
            a.setUser_phone(userphone);
            a.setAddress(address);
            int i = UserDao.addressInsert(a,uid);
            PrintWriter out = res.getWriter();
            out.write("<script>");
            out.write("alert('添加成功');");
            out.write("location.href='addressmanager'");
            out.write("</script>");
            out.close();
        }else{
            PrintWriter out = res.getWriter();
            out.write("<script>");
            out.write("alert('添加失败，输入不能为空！');");
            out.write("location.href='addressmanager'");
            out.write("</script>");
            out.close();
        }
    }

    //修改收货地址
    @RequestMapping(value = "/addresschange")
    public void changeAddress(@RequestParam("name")String username,@RequestParam("userphone")String userphone,@RequestParam("address")String address1,HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        if(!username.equals("")&&!userphone.equals("")&&!address1.equals("")) {

            String name = new String(username.getBytes("ISO-8859-1"),"UTF-8");
            String address = new String(address1.getBytes("ISO-8859-1"),"UTF-8");
            String addid=req.getParameter("id");

            HttpSession session=req.getSession();
            userinf user=(userinf)session.getAttribute("name");
            int uid=user.getUser_id();

            address a = new address();
            a.setUser_name(name);
            a.setUser_phone(userphone);
            a.setAddress(address);
            int i = UserDao.addressChange(a,Integer.parseInt(addid));
            PrintWriter out = res.getWriter();
            out.write("<script>");
            out.write("alert('修改成功');");
            out.write("location.href='addressmanager'");
            out.write("</script>");
            out.close();
        }else{
            PrintWriter out = res.getWriter();
            out.write("<script>");
            out.write("alert('修改失败，输入不能为空！');");
            out.write("location.href='addressmanager'");
            out.write("</script>");
            out.close();
        }
    }


    //删除地址
    @RequestMapping(value = "/deleteaddress")
    public void userDeleteManager(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");
        int a=UserDao.addressDelete(Integer.parseInt(id));
        resp.sendRedirect("addressmanager");

    }
}
