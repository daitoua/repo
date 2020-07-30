package com.daitou.o2o.service;

import com.daitou.o2o.entity.ProductCategory;
import com.daitou.o2o.entity.Shop;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategory(Long shopId);

}
