package com.test;

import com.SE.bean.address;
import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.controller.PreUserController;
import com.SE.dao.CollectDao;
import com.SE.dao.ItemDao;
import com.SE.dao.OrderDao;
import com.SE.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PreItemControllerTest {
    @Test
    public void detailTest(){
        item i= ItemDao.selectItemById(1);
        item i1=new item();
        i1.setItem_id(1);
        Assert.assertEquals(i1.getItem_id(),i.getItem_id());
    }
    @Test
    public void searchItemTest(){
        String text="草莓";
        ArrayList<item> list= ItemDao.searchItem(text);

    }
    @Test
    public void collectDeleteTest(){
        item i=new item();
        i=ItemDao.selectItemById(21);
        int a= CollectDao.collectDelete(2,i.getItem_id());
        int b=CollectDao.collectItemDelete(2,i.getItem_id());
        Assert.assertEquals(a,1);
        Assert.assertEquals(b,1);

    }
    @Test
    public void addressConfirmTest(){
        int a=OrderDao.addressConfirm(2,1);
        Assert.assertEquals(a,1);


    }
    @Test
    public void addressDeleteTest(){
        int add_id=1;
        int a=UserDao.addressDelete(add_id);
        Assert.assertEquals(a,1);


    }
    @Test
    public void addAddressTest(){
        String name="张三";
        String address="浙江省";
        String userphone="18656600273";
        address a = new address();
        a.setUser_name(name);
        a.setUser_phone(userphone);
        a.setAddress(address);
        int b=UserDao.addressInsert(a,2);
        Assert.assertEquals(b,1);


    }
    @Test
    public void changeAddressTest(){
        String name="张三";
        String address="浙江省";
        String userphone="18656600273";
        address a = new address();
        a.setUser_name(name);
        a.setUser_phone(userphone);
        a.setAddress(address);
        int add_id=2;
        int b=UserDao.addressChange(a,add_id);
        Assert.assertEquals(b,1);


    }


}