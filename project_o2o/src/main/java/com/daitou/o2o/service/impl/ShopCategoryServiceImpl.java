package com.daitou.o2o.service.impl;

import com.daitou.o2o.cache.JedisUtil;
import com.daitou.o2o.dao.ShopCategoryDao;
import com.daitou.o2o.entity.ShopCategory;
import com.daitou.o2o.service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;


    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {

        String key = SCLISTKEY;

        List<ShopCategory> shopCategoryList = null;

        ObjectMapper mapper = new ObjectMapper();

        if (shopCategoryCondition == null) {
            // 若查询条件为空，则列出所有首页大类，即parentId为空的店铺类别
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null
                && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            // 若parentId为非空，则列出该parentId下的所有子类别
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            // 列出所有子类别，不管其属于哪个类，都列出来
            key = key + "_allsecondlevel";
        }

        if (!jedisKeys.exists(key)){
            String jsonString = null;
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);

            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

            jedisStrings.set(key,jsonString);

        }else {

            String jsonString = jedisStrings.get(key);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);

            try {
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                throw new RuntimeException();
            }

        }






        return shopCategoryList;
    }
}
