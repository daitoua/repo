package com.daitou.o2o.service;

import com.daitou.o2o.Exception.ProductCategoryOperationException;
import com.daitou.o2o.dto.ProductCategoryExecution;
import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;

import java.util.List;

public interface ProductCategoryService {

    public static final String PRODUCTCATEGORYLISTKEY = "productcategorylist";

    List<ProductCategory> getProductCategory(Long shopId);

    ProductCategoryExecution addProductCategory(List<ProductCategory> productCategoryList)throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(long shopId, long productCategory);

}
