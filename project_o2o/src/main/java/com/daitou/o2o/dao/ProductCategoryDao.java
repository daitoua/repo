package com.daitou.o2o.dao;

import com.daitou.o2o.entity.Product;
import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * 通过shopID区查询产品
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategory(long shopId);

    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    int deleteProductCategory(@Param("shopId")long shopId,@Param("productCategoryId")long productCategory);



}
