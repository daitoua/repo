package com.daitou.o2o.dao;


import com.daitou.o2o.Exception.ShopOperationException;
import com.daitou.o2o.dto.ShopExecution;
import com.daitou.o2o.entity.*;
import com.daitou.o2o.enums.ShopStateEnum;
import com.daitou.o2o.service.AreaService;
import com.daitou.o2o.service.ShopService;
import com.daitou.o2o.utils.CodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class areaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopService shopService;

    @Test
    public void test() {
        List<Area> areas = areaDao.findAll();
        System.out.println(areas);

    }

    @Test
    public void test2() {

        List<Area> list = areaService.getAreaList();

        assertEquals("dsfsdf", list.get(0).getAreaName());
    }

    @Test
    public void test3() {
        Shop shop = new Shop();

        PersonInfo owner = new PersonInfo();

        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);

    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testAddShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        File shopImg = new File("/Users/daitou/work/image/daitoutou.png");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

    @Test
    public void test5() {

        String str = "abcdefg";
        System.out.println(new StringBuilder(str).reverse().toString());

        String str2 = "work/index/daitou.jpg";
        System.out.println(str2.substring(str2.lastIndexOf("/") + 1));


    }

    @Test
    public void test6() {

        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        Shop shop = new Shop();
        shop.setOwner(owner);
        Area area = new Area();
        area.setAreaId(1);
        shop.setArea(area);

        List<Shop> list = shopDao.queryShopList(shop, 0, 3);
        for (Shop shop1 : list) {
            System.out.println(shop1);
        }


    }


    @Test
    public void test7() {

        Shop shop = new Shop();
        shop.setShopId(1L);
        List<ProductCategory> list = productCategoryDao.queryProductCategory(shop.getShopId());
        System.out.println(list.size());


    }




}
