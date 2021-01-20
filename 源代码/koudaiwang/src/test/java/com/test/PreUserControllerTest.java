package com.test;

import com.SE.bean.orderinf;
import com.SE.bean.userinf;
import com.SE.controller.PreUserController;
import com.SE.dao.OrderDao;
import com.SE.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PreUserControllerTest {
    PreUserController pu=new PreUserController();
    @Test
    public void userRegisterTest(){
        String username="uh";
        String userphone="18656600273";
        String pay="11";
        String userpwd="111";
        userinf user=new userinf();
        user.setUser_name(username);
        user.setUser_phone(userphone);
        user.setUser_pay(pay);
        user.setUser_pwd(userpwd);
        int i= UserDao.userInsert(user);
        Assert.assertEquals(i,1);
}
    @Test
    public void userLoginTest(){
        String username="zs";
        String userpwd="111";
        int a = UserDao.selectByName(username,userpwd);
        Assert.assertEquals(a,1);
    }
    @Test
    public void payCheckedTest(){
        int a= OrderDao.itemConfirm(21);
        Assert.assertEquals(a,1);
    }
    @Test
    public void mySellToSendTest(){
        ArrayList<orderinf> olist1=new ArrayList<orderinf>();
        orderinf o=new orderinf();
        o.setUser_id(2);
        o.setItem_id(22);
        o.setOrder_num(1);
        ArrayList<orderinf> olist=OrderDao.selectOrderInfToSend(2);
        olist1.add(o);
        Assert.assertEquals(olist1,olist);
    }

}