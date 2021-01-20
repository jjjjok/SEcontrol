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
    @RequestMapping(value = "quit")
    public String quit(HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{

        HttpSession session=req.getSession();
        session.removeAttribute("name");
        session.removeAttribute("isLogin");
        return "login";
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
    //转至换货更新物流页面
    @RequestMapping(value = "/toaddsendchange")
    public void toAddSendChange(@RequestParam("id")String oid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{

        req.setAttribute("oid", oid);
        req.getRequestDispatcher("/WEB-INF/jsp/addsendchange.jsp").forward(req, res);

    }

    //转至退款页面
    @RequestMapping(value = "/torefund")
    public void toRefund(@RequestParam("id")String oid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{

        req.setAttribute("oid", oid);
        req.getRequestDispatcher("/WEB-INF/jsp/refund.jsp").forward(req, res);

    }

    //转至换货页面
    @RequestMapping(value = "/togoodchange")
    public void toGoodChange(@RequestParam("id")String oid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{

        req.setAttribute("oid", oid);
        req.getRequestDispatcher("/WEB-INF/jsp/goodchange.jsp").forward(req, res);

    }

    //转至申诉页面
    @RequestMapping(value = "/toappeal")
    public void toAppeal(@RequestParam("id")String oid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{

        req.setAttribute("oid", oid);
        req.getRequestDispatcher("/WEB-INF/jsp/appeal.jsp").forward(req, res);

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
    public void userLogin(@RequestParam("name")String username1, @RequestParam("password")String userpwd, HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        String username = new String(username1.getBytes("ISO-8859-1"),"UTF-8");
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
    public void userNameCheck(@RequestParam("username")String username,HttpServletResponse res,HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");

        int count=UserDao.userSearchByName(username);
        PrintWriter out = res.getWriter();
        if(count>0){
            out.print("false");
        }else{
            out.print("true");
        }
        out.close();
    }
    //检查物流编号是否存在
    @RequestMapping(value = "/sendidcheck")
    @ResponseBody
    public void snedIdCheck(@RequestParam("sendid")String username,HttpServletResponse res) throws IOException {
        int count=UserDao.sendSearchById(username);
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
    public void userPhoneCheck(@RequestParam("username")String username,@RequestParam("userphone")String userphone, HttpServletResponse res,HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        if(!username.equals("")){

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
    public void updatePwd(@RequestParam("username")String username1,@RequestParam("userpwd")String pwd ,HttpServletResponse res,HttpServletRequest req) throws IOException {
        String username = new String(username1.getBytes("ISO-8859-1"),"UTF-8");
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
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
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
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('需要登录，即将前往登录');");
            out.write("location.href='/kdw/user/tologin'");
            out.write("</script>");
            out.close();
        }


    }

    //显示我的等待发货订单
    @RequestMapping(value = "/myordernotsend")
    public void myOrderNotSend(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInfByState(uid,1);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myordernotsend.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }
    }
    //显示我的派送中订单
    @RequestMapping(value = "/myordersended")
    public void myOrderSended(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInfByState(uid,2);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myordersended.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }
    }
    //显示我的退款中订单
    @RequestMapping(value = "/myorderrefunding")
    public void myOrderRefunding(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInfRefunding(uid);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myorderrefunding.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }
    }
    //显示我的申诉中中订单
    @RequestMapping(value = "/myorderappealing")
    public void myOrderAppealing(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInfByState(uid,8);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myorderappeal.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }
    }
    //显示我的完成订单
    @RequestMapping(value = "/myorderfinish")
    public void myOrderFinish(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInfFinish(uid);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myorderfinish.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }
    }
    //显示我的换货订单
    @RequestMapping(value = "/myorderchange")
    public void myOrderChange(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> orderlist = OrderDao.selectOrderInfChange(uid);

            req.setAttribute("orderlist", orderlist);

            req.getRequestDispatcher("/WEB-INF/jsp/myorderchange.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }
    }

    //显示申诉处理结果
    @RequestMapping(value = "/appealresultdetail")
    public void appealResultDetail(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{


        String id=req.getParameter("id");
        appeal r=AppealDao.getAppealDetailById(Integer.parseInt(id));
        int uid=r.getUser_id();
        userinf user=UserDao.selectById(uid);
        String username = user.getUser_name();
        String userphone=user.getUser_phone();
        req.setAttribute("appeal", r);
        req.setAttribute("username", username);
        req.setAttribute("userphone", userphone);

        req.getRequestDispatcher("/WEB-INF/jsp/appealresultdetail.jsp").forward(req, resp);

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

    //显示用户退款的订单
    @RequestMapping(value = "/mysellrefund")
    public void mySellRefund(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> olist=OrderDao.selectOrderInfRefund(uid);


            req.setAttribute("orderlist", olist);

            req.getRequestDispatcher("/WEB-INF/jsp/mysellrefund.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }

    }

    //显示用户换货的订单
    @RequestMapping(value = "/mysellchange")
    public void mySellChange(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> olist=OrderDao.selectSellOrderInfChange(uid);


            req.setAttribute("orderlist", olist);

            req.getRequestDispatcher("/WEB-INF/jsp/mysellchange.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }

    }

    //显示卖家已完成的订单
    @RequestMapping(value = "/mysellfinish")
    public void mySellFinish(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            ArrayList<orderinf> olist=OrderDao.selectSellOrderInfFinish(uid);

            req.setAttribute("orderlist", olist);

            req.getRequestDispatcher("/WEB-INF/jsp/mysellfinish.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }

    }



    //显示退款详情
    @RequestMapping(value = "/refunddetail")
    public void refundDetail(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            String userphone=user.getUser_phone();
            String id=req.getParameter("id");
            refund r=RefundDao.getRefundDetailById(Integer.parseInt(id));
            req.setAttribute("refund", r);
            req.setAttribute("username", username);
            req.setAttribute("userphone", userphone);

            req.getRequestDispatcher("/WEB-INF/jsp/refunddetail.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }

    }

    //显示换货详情
    @RequestMapping(value = "/goodchangedetail")
    public void goodChangeDetail(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String username = user.getUser_name();
            String userphone=user.getUser_phone();
            String id=req.getParameter("id");
            goodchange r=GoodChangeDao.getChangeDetailById(Integer.parseInt(id));
            req.setAttribute("refund", r);
            req.setAttribute("username", username);
            req.setAttribute("userphone", userphone);

            req.getRequestDispatcher("/WEB-INF/jsp/goodchangedetail.jsp").forward(req, resp);
        }  else{
            resp.sendRedirect("/kdw/user/tologin");
        }

    }




    //显示物流详情
    @RequestMapping(value = "/senddetail")
    public void sendDetail(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            String id=req.getParameter("id");
            orderinf oi=OrderDao.selectOrderById(Integer.parseInt(id));
            wuliu wl=OrderDao.selectWuliuById(Integer.parseInt(id));

            req.setAttribute("order", oi);
            req.setAttribute("w", wl);

            req.getRequestDispatcher("/WEB-INF/jsp/senddetail.jsp").forward(req, resp);
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
    //换货更新物流信息
    @RequestMapping("/addsendchange")

    public void addSendChange(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String order_id1=req.getParameter("oid");
        String name=req.getParameter("name");
        String wuliu_id=req.getParameter("sendid");

        int order_id = Integer.parseInt(order_id1);
        SellDao.updateSend(order_id, name, wuliu_id);

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

    //商家退款处理
    @RequestMapping(value = "/refundchecked")
    public void itemChecked(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String url = req.getParameter("url");
        String id = req.getParameter("id");
        RefundDao.updateRefundCheck(Integer.parseInt(id),url);
        resp.sendRedirect("mysellrefund");

    }
    //商家换货处理
    @RequestMapping(value = "/changechecked")
    public void itemChangeChecked(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String url = req.getParameter("url");
        String id = req.getParameter("id");
        GoodChangeDao.updateChangeCheck(Integer.parseInt(id),url);
        resp.sendRedirect("mysellchange");

    }


}
