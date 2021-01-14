package com.test;

import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.controller.ManagerUserController;
import com.SE.controller.PreUserController;
import com.SE.dao.ItemDao;
import com.SE.dao.PageDao;
import com.SE.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ManagerUserControllerTest {
    @Test
    public void selectUserByNameTest() {
        int count=10;
        int name=1;
        int result[]= PageDao.userNamePage(count,name);
        ArrayList<userinf> list= UserDao.selectUserByName(2,count,name);

    }
    @Test
    public void userUpdateManagerTest() {
        userinf user=UserDao.selectById(1);

    }
    @Test
    public void userDeleteManagerTest() {
        int a=UserDao.userDelete(2);
        Assert.assertEquals(a,1);

    }
    @Test
    public void managerLoginTest() {
        int a = UserDao.selectManagerByName("2","2");
        Assert.assertEquals(a,1);

    }


}