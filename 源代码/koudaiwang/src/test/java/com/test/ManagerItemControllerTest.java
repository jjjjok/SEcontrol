package com.test;

import com.SE.bean.item;
import com.SE.controller.ManagerItemController;
import com.SE.dao.AppealDao;
import com.SE.dao.ItemDao;
import com.SE.dao.PageDao;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ManagerItemControllerTest {

    ManagerItemController mic=new ManagerItemController();
    @Test
    public void itemDeleteManagerTest() {
        int a=ItemDao.itemDelete(24);
        Assert.assertEquals(a,0);

    }
    @Test
    public void appealResultTest() {
        String result="aaaaa";
        int a= AppealDao.appealUpdate(result,150);
        Assert.assertEquals(a,1);

    }
    @Test
    public void selectUserByNameTest() {
        int count=10;
        String name="zs";
        int result[]= PageDao.itemNamePage(count,name);
        ArrayList<item> list=ItemDao.selectItemByName(2,count,name);

    }
    @Test
    public void itemCheckedTest() {
       int a=ItemDao.updateCheck(21);
        Assert.assertEquals(a,1);

    }
    @Test
    public void payCheckedTest() {
        int a=ItemDao.payChecked(21);
        Assert.assertEquals(a,1);

    }


}