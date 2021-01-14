package com.SE.controller;


import com.SE.bean.*;
import com.SE.dao.*;
import com.SE.port.MimiPaySample;
import com.SE.port.NotifyParams;
import com.SE.port.PayParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
    public void detail(HttpServletResponse resp, HttpServletRequest req)throws ServletException, IOException{
        String id1=req.getParameter("id");
        int id=Integer.parseInt(id1);
        item i=ItemDao.selectItemById(id);
        req.setAttribute("i",i);


        req.getRequestDispatcher("/WEB-INF/jsp/prodetail.jsp").forward(req,resp);
    }
    //订单确认时转至添加地址页面
    @RequestMapping(value = "/toaddaddress")
    public String toAddAddressOrder(){
        return "addaddressorder";
    }

    //转至回调页
    @RequestMapping(value = "/topayok")
    public String toPayok(){
        return "payok";
    }

    //转至修改地址页面
    @RequestMapping(value = "/tochangeaddress")
    public void toAddAddress(@RequestParam("id")String aid,HttpServletResponse res, HttpServletRequest req)throws ServletException,IOException{
        address a=UserDao.selectAddressByAddId(Integer.parseInt(aid));
        req.setAttribute("addlist", a);
        req.getRequestDispatcher("/WEB-INF/jsp/changeaddressorder.jsp").forward(req, res);

    }

    @RequestMapping("/tousersellpage")
    public String toUserSellPage(){
        return "usersell";
    }

//发布商品
    @RequestMapping("/tousersell")
public void toUserSell(HttpServletResponse resp,HttpServletRequest req)throws ServletException, IOException{
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/html;charset=UTF-8");
    HttpSession session=req.getSession();
    String islogin=(String)session.getAttribute("isLogin");
    userinf user=(userinf)session.getAttribute("name");
    if(user!=null&&islogin.equals("1")) {
        resp.sendRedirect("/kdw/item/tousersellpage");
    }else{
        PrintWriter out = resp.getWriter();
        out.write("<script>");
        out.write("alert('需要登录，即将前往登录');");
        out.write("location.href='/kdw/user/tologin'");
        out.write("</script>");
        out.close();
    }
}


    //支付
    @RequestMapping(value = "/pay")
    public void dopay(HttpServletResponse response,HttpServletRequest req)throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String confirm_id = req.getParameter("confirmid");
        String sum = req.getParameter("sum");
        HttpSession session = req.getSession();
        userinf user = (userinf) session.getAttribute("name");
        int uid = user.getUser_id();
        int count = OrderDao.selectAddressStateByUid(uid);

        if (count > 0) {

            PayParams pp = new PayParams();
            pp.setPrice(Float.parseFloat(sum));
            pp.setType(1);
            pp.setOutTradeNo("nky" + confirm_id);
            pp.setOutUserNo(String.valueOf(uid));

            pp.setNotifyUrl("http://121.196.159.188:8080/kdw/item/topayok");
            pp.setReturnUrl("http://121.196.159.188:8080/kdw/item/topayok");
            MimiPaySample mm = new MimiPaySample();
            mm.pay(pp, response);
        } else {
            String str="";
            int confirm_id1=Integer.parseInt(confirm_id);
            int max=OrderDao.selectMaxOId(confirm_id1);
            int min=OrderDao.selectMinOId(confirm_id1);
            for(int i=min;i<=max;i++){
                if (i-1==max){
                    str+=OrderDao.selectIIdByOid(i);
                }else{
                    str+=OrderDao.selectIIdByOid(i)+",";
                }
            }

            PrintWriter out = response.getWriter();
            out.write("<script>alert('请选择一个收货地址！');location.href='collectshow'</script>");
            out.close();


        }
    }
//购买后更新物品数量和订单信息
    @RequestMapping(value = "/updatenum")
    private void writeData2DB(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{

        HttpSession session=req.getSession();
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        String addid=UserDao.addSearchByUser(uid);
        address a=UserDao.addNameSearchByUser(uid,addid);

        int confirm_id=OrderDao.selectMaxOIdByUid(uid);

        OrderDao.updateOrder(confirm_id);
        int max=OrderDao.selectMaxOId(confirm_id);
        int min=OrderDao.selectMinOId(confirm_id);
        for(int i=min;i<=max;i++){
            int item_id=ItemDao.selectIdByOId(i);
            int ordernum=ItemDao.selectOrderNumByOId(i);
            int num= ItemDao.selectNumById(item_id);
            int count=num-ordernum;
            if(count>0){

            ItemDao.updateItemNum(item_id,count);
            ItemDao.updateCIItemNum(item_id,num);
            }
            else{

                ItemDao.updateAfterBuy(item_id);
            }
            OrderDao.addressOrderAdd(i,addid,a.getUser_name(),a.getUser_phone());
        }
        resp.sendRedirect("/kdw/user/myorder");
    }



    //首页点击分类显示商品
    @RequestMapping(value = "/selectflitem")
    public void selectFlItem(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{

        int cpage=1;//当前页
        int count=8;//每页显示数目
        String cp=req.getParameter("cp");
        if(cp!=null){
            cpage=Integer.parseInt(cp);
        }
        String id1=req.getParameter("id");
        int id=Integer.parseInt(id1);
        int result[]= PageDao.itemByFlPage(count,id);

        ArrayList<item> list= ItemDao.selectItemByFlId(cpage,count,id);
        req.setAttribute("id",id1);
        req.setAttribute("list",list);
        req.setAttribute("itemsum",result[0]);
        req.setAttribute("itempage",result[1]);
        req.setAttribute("itemcpage",cpage);
        req.getRequestDispatcher("/WEB-INF/jsp/productlist.jsp").forward(req,resp);
    }

    //搜索框搜索商品
    @RequestMapping(value = "/searchitem")
    public void searchItem(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        String text=req.getParameter("text");

        ArrayList<item> list= ItemDao.searchItem(text);
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
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
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
            int nownum=ItemDao.selectNumById(i.getItem_id());
           if(flag>0){
               int ccount=CollectDao.selectCollectNumByid(uid,i.getItem_id());
               int num=Integer.parseInt(count)+ccount;

               if(nownum>num) {
                   CollectDao.updateNum(num, uid, i.getItem_id());
                   CollectDao.updateCINum(num, uid, i.getItem_id());
               }else{
                   CollectDao.updateNum(nownum, uid, i.getItem_id());
                   CollectDao.updateCINum(nownum, uid, i.getItem_id());
               }
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
               l.setItem_num(nownum);
               l.setUser_id(uid);
               CollectDao.collectItemInsert(l);

           }
            if (url.equals("1")){
                resp.sendRedirect("collectshow");
            }else if(url.equals("2")){

                resp.sendRedirect("detail?id="+item_id);
            }


        }else{
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('需要登录，即将前往登录');");
            out.write("location.href='/kdw/user/tologin'");
            out.write("</script>");
            out.close();
        }
    }
    //购物车页面
    @RequestMapping(value = "/collectshow")
    public void collectShow(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        if(user!=null&&islogin.equals("1")) {
            int uid = user.getUser_id();
            ArrayList<collectitem> list = CollectDao.selectCollectByUId(uid);
            int count=CollectDao.selectCountByUId(uid);



            req.setAttribute("list",list);
            req.setAttribute("count",count);
            req.getRequestDispatcher("/WEB-INF/jsp/collect.jsp").forward(req,resp);

        }
        else{
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('需要登录，即将前往登录');");
            out.write("location.href='/kdw/user/tologin'");
            out.write("</script>");
            out.close();
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
        int nownum=ItemDao.selectNumById(i.getItem_id());
        if(Integer.parseInt(count)>nownum){
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('超过库存数量！');");
            out.write("location.href='/kdw/item/collectshow'");
            out.write("</script>");
            out.close();

        }else {

            CollectDao.updateNum(Integer.parseInt(count), uid, i.getItem_id());
            CollectDao.updateCINum(Integer.parseInt(count), uid, i.getItem_id());
        }

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
        CollectDao.collectDelete(uid,i.getItem_id());
        CollectDao.collectItemDelete(uid,i.getItem_id());
        resp.sendRedirect("collectshow");

    }
    //购物车批量删除
    @RequestMapping(value = "/collectsdelete")
    public void collectsDelete(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{


        String id[] = req.getParameterValues("cid");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();

        for(int i=0;i< id.length;i++){
            item it=new item();
            it=ItemDao.selectItemById(Integer.parseInt(id[i]));
            CollectDao.collectDelete(uid,it.getItem_id());
            CollectDao.collectItemDelete(uid,it.getItem_id());
        }
        resp.sendRedirect("collectshow");

    }



    //订单确认
    @RequestMapping(value = "/orderconfirm")
    public void orderConfirm(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        int confirmid= OrderDao.selectMaxCId();
        if(confirmid>0){
            confirmid+=1;
        }else {
            confirmid=1;
        }
        String itemids=req.getParameter("itemids");
        if(!itemids.equals("")){
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        String ids[]=itemids.split(",");
        ArrayList<collectitem> list=new ArrayList<collectitem>();
        float sum=0;
        for(int i=0;i< ids.length;i++){
            collectitem p=CollectDao.selectCollectByUIId(uid,Integer.parseInt(ids[i]));
            String img=p.getItem_img();
            float bprice=p.getItem_bprice();
            String item_name=p.getItem_name();
            String seller_pay=CollectDao.selectPayById(Integer.parseInt(ids[i]));
            String seller_name=CollectDao.selectSellNameById(Integer.parseInt(ids[i]));
            String buyer_pay=user.getUser_pay();

            int v=OrderDao.orderAdd(confirmid,uid,p.getItem_id(),p.getCollect_count(),img,bprice,item_name,seller_pay,buyer_pay,seller_name);
            float price=p.getCollect_count()*p.getItem_bprice();
            sum+=price;
            list.add(p);
        }

        ArrayList<address> add=UserDao.selectUserAddress(uid);
        req.setAttribute("addlist",add);
        req.setAttribute("confirmlist",list);
        req.setAttribute("sum",sum);
        req.setAttribute("confirm",confirmid);
        req.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(req,resp);}
        else{
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('请选择一件商品！');");
            out.write("location.href='collectshow'");
            out.write("</script>");
            out.close();

        }

    }
    //确认订单时选择收货地址
    @RequestMapping(value = "/addressconfirm")
    public void addressConfirm(HttpServletResponse resp,HttpServletRequest req) throws ServletException,IOException{
        String str="";
        String addid=req.getParameter("addid");
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        OrderDao.addressConfirm(uid,Integer.parseInt(addid));
        int confirm_id=OrderDao.selectMaxCIdByUid(uid);
        int max=OrderDao.selectMaxOId(confirm_id);
        int min=OrderDao.selectMinOId(confirm_id);
        for(int i=min;i<=max;i++){
            if (i-1==max){
                str+=OrderDao.selectIIdByOid(i);
            }else{
                str+=OrderDao.selectIIdByOid(i)+",";
            }
        }

        req.getRequestDispatcher("orderconfirm?itemids="+str).forward(req,resp);

    }
    //删除单个地址
    @RequestMapping(value = "/deleteaddress")
    public void addressDelete(HttpServletResponse resp,HttpServletRequest req)throws ServletException,IOException{
        String id = req.getParameter("id");
        UserDao.addressDelete(Integer.parseInt(id));
        String str="";
        HttpSession session=req.getSession();
        String islogin=(String)session.getAttribute("isLogin");
        userinf user=(userinf)session.getAttribute("name");
        int uid=user.getUser_id();
        int confirm_id=OrderDao.selectMaxCIdByUid(uid);
        int max=OrderDao.selectMaxOId(confirm_id);
        int min=OrderDao.selectMinOId(confirm_id);
        for(int i=min;i<=max;i++){
            if (i-1==max){
                str+=OrderDao.selectIIdByOid(i);
            }else{
                str+=OrderDao.selectIIdByOid(i)+",";
            }
        }

        req.getRequestDispatcher("orderconfirm?itemids="+str).forward(req,resp);
    }

    //添加收货地址
    @RequestMapping(value = "/addressadd")
    public void addAddress(@RequestParam("name")String username,@RequestParam("userphone")String userphone,@RequestParam("address")String address1,HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        if(username!=null&&userphone!=null&&address1!=null) {

            String name = new String(username.getBytes("ISO-8859-1"),"UTF-8");
            String address = new String(address1.getBytes("ISO-8859-1"),"UTF-8");
            String str="";
            HttpSession session=req.getSession();
            userinf user=(userinf)session.getAttribute("name");
            int uid=user.getUser_id();

            address a = new address();
            a.setUser_name(name);
            a.setUser_phone(userphone);
            a.setAddress(address);
            UserDao.addressInsert(a,uid);
            int confirm_id=OrderDao.selectMaxCIdByUid(uid);
            int max=OrderDao.selectMaxOId(confirm_id);
            int min=OrderDao.selectMinOId(confirm_id);
            for(int i=min;i<=max;i++){
                if (i-1==max){
                    str+=OrderDao.selectIIdByOid(i);
                }else{
                    str+=OrderDao.selectIIdByOid(i)+",";
                }
            }

            req.getRequestDispatcher("orderconfirm?itemids="+str).forward(req,res);
        }
    }

    //修改收货地址
    @RequestMapping(value = "/addresschange")
    public void changeAddress(@RequestParam("name")String username,@RequestParam("userphone")String userphone,@RequestParam("address")String address1,HttpServletResponse res, HttpServletRequest req) throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        String str="";
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
            UserDao.addressChange(a,Integer.parseInt(addid));

            int confirm_id=OrderDao.selectMaxCIdByUid(uid);
            int max=OrderDao.selectMaxOId(confirm_id);
            int min=OrderDao.selectMinOId(confirm_id);
            for(int i=min;i<=max;i++){
                if (i-1==max){
                    str+=OrderDao.selectIIdByOid(i);
                }else{
                    str+=OrderDao.selectIIdByOid(i)+",";
                }
            }

            req.getRequestDispatcher("orderconfirm?itemids="+str).forward(req,res);

        }
    }

}
