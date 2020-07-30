package com.daitou.o2o.service.impl;

import com.daitou.o2o.dao.ProductCategoryDao;
import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;
import com.daitou.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Override
    public List<ProductCategory> getProductCategory(Long shopId) {


        return productCategoryDao.queryProductCategory(shopId);
    }
}
