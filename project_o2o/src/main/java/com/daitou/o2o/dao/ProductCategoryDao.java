package com.daitou.o2o.dao;

import com.daitou.o2o.entity.Product;
import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * 通过shopID区查询产品
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategory(long shopId);



}
